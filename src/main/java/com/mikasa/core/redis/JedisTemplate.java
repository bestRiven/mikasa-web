package com.mikasa.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JedisTemplate 提供了一个template方法，负责对Jedis连接的获取与归还。 JedisAction<T> 和
 * JedisActionNoResult两种回调接口，适用于有无返回值两种情况。 同时提供一些JedisOperation中定义的 最常用函数的封装,
 * 如get/set/zadd等。
 */
public class JedisTemplate {

	private static Logger logger = LoggerFactory.getLogger(JedisTemplate.class);

	private Pool<Jedis> jedisPool;

	public JedisTemplate(Pool<Jedis> jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 执行有返回结果的action。
	 */
	public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			return jedisAction.action(jedis);
		} catch (JedisConnectionException e) {
			logger.error("Redis connection lost.", e);
			broken = true;
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 执行无返回结果的action。
	 */
	public void execute(JedisActionNoResult jedisAction) throws JedisException {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = jedisPool.getResource();
			jedisAction.action(jedis);
		} catch (JedisConnectionException e) {
			logger.error("Redis connection lost.", e);
			broken = true;
			throw e;
		} finally {
			closeResource(jedis, broken);
		}
	}

	/**
	 * 根据连接是否已中断的标志，分别调用returnBrokenResource或returnResource。
	 */
	protected void closeResource(Jedis jedis, boolean connectionBroken) {
		if (jedis != null) {
			try {
				if (connectionBroken) {
					jedisPool.returnBrokenResource(jedis);
				} else {
					jedisPool.returnResource(jedis);
				}
			} catch (Exception e) {
				logger.error("Error happen when return jedis to pool, try to close it directly.", e);
				JedisUtils.closeJedis(jedis);
			}
		}
	}

	/**
	 * 获取内部的pool做进一步的动作。
	 */
	public Pool<Jedis> getJedisPool() {
		return jedisPool;
	}

	/**
	 * 有返回结果的回调接口定义。
	 */
	public interface JedisAction<T> {
		T action(Jedis jedis);
	}

	/**
	 * 无返回结果的回调接口定义。
	 */
	public interface JedisActionNoResult {
		void action(Jedis jedis);
	}

	// ////////////// 常用方法的封装 ///////////////////////// //

	// //////////公共 ///////////////////////////

	/**
	 * exists
	 */
	public Boolean exists(final String key) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	public Boolean exists(final byte[] key) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	/**
	 * 删除key, 如果key存在返回true, 否则返回false。
	 */
	public Boolean del(final String... keys) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.del(keys) == 1 ? true : false;
			}
		});
	}

	public Boolean del(final byte[]... keys) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.del(keys) == 1 ? true : false;
			}
		});
	}

	public Long expire(final String key, final int seconds) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	// ////////////// 关于String ///////////////////////////

	/**
	 * 如果key不存在, 返回null.
	 */

	public String get(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	public byte[] get(final byte[] key) {
		return execute(new JedisAction<byte[]>() {

			@Override
			public byte[] action(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	/**
	 * 如果key不存在, 返回null.
	 */
	public Long getAsLong(final String key) {
		String result = get(key);
		return result != null ? Long.valueOf(result) : null;
	}

	/**
	 * 如果key不存在, 返回null.
	 */
	public Integer getAsInt(final String key) {
		String result = get(key);
		return result != null ? Integer.valueOf(result) : null;
	}

	public void set(final String key, final String value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.set(key, value);
			}
		});
	}

	public void setex(final String key, final String value, final int seconds) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.setex(key, seconds, value);
			}
		});
	}

	public void setex(final byte[] key, final byte[] obj, final int seconds) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.setex(key, seconds, obj);
			}
		});
	}

	/**
	 * 如果key还不存在则进行设置，返回true，否则返回false.
	 */

	public Boolean setnx(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.setnx(key, value) == 1 ? true : false;
			}
		});
	}

	/**
	 * 综合setNX与setEx的效果。
	 */

	public Boolean setnxex(final String key, final String value, final int seconds) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				String result = jedis.set(key, value, "NX", "EX", seconds);
				return JedisUtils.isStatusOk(result);
			}
		});
	}

	public Long incr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	public Long decr(final String key) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}
	
	public Set<String> keys(final String key) {
		return execute(new JedisAction<Set<String>>() {
			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.keys(key);
			}
		});
	}
	
	

	// ////////////// 关于Hash ///////////////////////////
	/**
	 * 返回哈希表 key 中给定域 field 的值。
	 */
	public String hget(final String key, final String field) {
		return execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}
	
	public String hgetNullToZero(final String key, final String field) {
		String ret= execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
		if(ret==null){
			return "0";
		}
		return ret;
	}
	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。 如果域
	 * field 已经存在于哈希表中，旧值将被覆盖。
	 */
	public void hset(final String key, final String field, final String value) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.hset(key, field, value);
			}
		});
	}
	/**
	 * 返回哈希表 key 中，一个或多个给定域的值。 如果给定的域不存在于哈希表，那么返回一个 nil 值。 因为不存在的 key
	 * 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
	 */
	public List<String> hmget(final String key, final String[] fields) {
		return execute(new JedisAction<List<String>>() {
			@Override
			public List<String> action(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}
	/**
	 * 返回哈希表 key 中，所有的域和值。 在返回值里，紧跟每个域名(field
	 * name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
	 */
	public Map<String, String> hmgetAll(final String key) {
		return execute(new JedisAction<Map<String, String>>() {
			@Override
			public Map<String, String> action(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}
	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 此命令会覆盖哈希表中已存在的域。 如果 key
	 * 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 */
	public void hmset(final String key, final Map<String, String> map) {
		execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.hmset(key, map);
			}
		});
	}
	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 */
	public Long hdel(final String key, final String... fieldsName) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.hdel(key, fieldsName);
			}
		});
	}
	/**
	 * 返回哈希表 key 中的所有域。
	 */
	public Set<String> hkeys(final String key) {
		return execute(new JedisAction<Set<String>>() {
			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.hkeys(key);
			}
		});
	}
	/**
	 * 返回哈希表 key 中所有域的值。
	 */
	public List<String> hvals(final String key) {
		return execute(new JedisAction<List<String>>() {
			@Override
			public List<String> action(Jedis jedis) {
				return jedis.hvals(key);
			}
		});
	}
	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。
	 */
	public boolean hexist(final String key, final String fieldName) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				return jedis.hexists(key, fieldName);
			}
		});
	}

	// ////////////// 关于List ///////////////////////////
	public void lpush(final String key, final String... values) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.lpush(key, values);
			}
		});
	}

	public String rpop(final String key) {
		return execute(new JedisAction<String>() {

			@Override
			public String action(Jedis jedis) {
				return jedis.rpop(key);
			}
		});
	}

	/**
	 * 获取list中的所有的值
	 */
	public List<String> lrangeAll(final String key){
		return execute(new JedisAction<List<String>>() {
			@Override
			public List<String> action(Jedis jedis) {
				return jedis.lrange(key,0,-1);
			}
		});
	}

	/**
	 * 返回List长度, key不存在时返回0，key类型不是list时抛出异常.
	 */
	public Long llen(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	/**
	 * 删除List中的第一个等于value的元素，value不存在或key不存在时返回false.
	 */
	public Boolean lremOne(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 1, value);
				return (count == 1);
			}
		});
	}

	/**
	 * 删除List中的所有等于value的元素，value不存在或key不存在时返回false.
	 */
	public Boolean lremAll(final String key, final String value) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				Long count = jedis.lrem(key, 0, value);
				return (count > 0);
			}
		});
	}

	// ////////////// 关于Sorted Set ///////////////////////////

	/**
	 * 加入Sorted set, 如果member在Set里已存在, 只更新score并返回false, 否则返回true.
	 */
	public Boolean zadd(final String key, final String member, final double score) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zadd(key, score, member) == 1 ? true : false;
			}
		});
	}

	/**
	 * 删除sorted set中的元素，成功删除返回true，key或member不存在返回false。
	 */
	public Boolean zrem(final String key, final String member) {
		return execute(new JedisAction<Boolean>() {

			@Override
			public Boolean action(Jedis jedis) {
				return jedis.zrem(key, member) == 1 ? true : false;
			}
		});
	}

	/**
	 * 当key不存在时返回null.
	 */
	public Double zscore(final String key, final String member) {
		return execute(new JedisAction<Double>() {

			@Override
			public Double action(Jedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	/**
	 * 返回sorted set长度, key不存在时返回0.
	 */
	public Long zcard(final String key) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zcard(key);
			}
		});
	}

	/**
	 * 返回rank排名
	 *
	 * @author gavin
	 */
	public Long zrank(final String key, final String member) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	/**
	 * 返回rank排名集合
	 *
	 * @author gavin
	 */
	public Set<String> zrange(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	/**
	 * 返回有序数组的大小
	 *
	 * @author gavin
	 */
	public Long zcount(final String key, final double min, final double max) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.zcount(key, min, max);
			}
		});
	}

	/**
	 * 返回rank倒序排名
	 *
	 * @author gavin
	 */
	public Long zrevrank(final String key, final String member) {
		return execute(new JedisAction<Long>() {

			@Override
			public Long action(Jedis jedis) {
				return jedis.zrevrank(key, member);
			}
		});
	}

	/**
	 * 返回rank倒序排名集合
	 *
	 * @author gavin
	 */
	public Set<String> zrevrange(final String key, final int start, final int end) {
		return execute(new JedisAction<Set<String>>() {

			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	public Boolean sadd(final String key, final String... members) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				return jedis.sadd(key, members) == 1 ? true : false;
			}
		});
	}

	public Boolean sismember(final String key, final String member) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}

	public Set<String> smembers(final String key) {
		return execute(new JedisAction<Set<String>>() {
			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	public Boolean srem(final String key, final String... member) {
		return execute(new JedisAction<Boolean>() {
			@Override
			public Boolean action(Jedis jedis) {
				return jedis.srem(key, member) == 1 ? true : false;
			}
		});
	}

	public Long publish(final String channel, final String message) {
		return execute(new JedisAction<Long>() {
			@Override
			public Long action(Jedis jedis) {
				return jedis.publish(channel, message);
			}
		});
	}

	public void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
		execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.subscribe(jedisPubSub, channels);
			}
		});
	}

	public String flushAll() {
		return execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				return jedis.flushAll();
			}
		});
	}

	public String flushDB() {
		return execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				return jedis.flushDB();
			}
		});
	}

	public Set<String> getAllkeys() {
		return execute(new JedisAction<Set<String>>() {
			@Override
			public Set<String> action(Jedis jedis) {
				return jedis.keys("*");
			}
		});
	}
}
