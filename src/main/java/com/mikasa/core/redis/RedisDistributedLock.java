package com.mikasa.core.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by zhangyu on 16/8/9.
 */

@Service
public class RedisDistributedLock {

    Logger log = LoggerFactory.getLogger(RedisDistributedLock.class);

    @Autowired
    private JedisTemplate jedisTemplate;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

    }


    public Map<String,Object> handle() throws Exception{
        HandleCall handleCall = new HandleCall();
        FutureTask task = new FutureTask(handleCall);
        threadPool.submit(task);
        Map<String,Object> result = (Map<String,Object>)task.get(3000,TimeUnit.MILLISECONDS);
        return result;
    }

    private void destroy(){
        threadPool.shutdown();
    }

    class HandleCall implements Callable<Map<String,Object>> {

        @Override
        public Map<String,Object> call() throws Exception {
            Map<String,Object> map = new ConcurrentHashMap<String, Object>();
            String lockKey = "key";
            String lockValue = "value";
            try {
                /**
                 * 每隔0.5秒尝试一次获得锁，总共60次，也就是30秒仍旧未获得锁，则直接return
                 */
                if(tryLock(lockKey,lockValue,60,0,60,500)){
                    map.put("task","获取redis分布式锁成功");

                }else {
                    map.put("task","获取redis分布式锁失败");
                }
            } catch (Exception e) {
                log.error("获取redis分布式锁异常",e);
            } finally {
                unLock(lockKey,lockValue);
            }
            return map;
        }
    }

    /**
     * 实现分布式锁
     * @param lockKey
     * @param lockValue
     * @param seconds 超时时间
     * @param currentRetryFrequency
     * @param retryFrequency 重试时间
     * @param intervalPeriodMillisecond 重试间隔
     */
    public  boolean tryLock(String lockKey,String lockValue,int seconds,int currentRetryFrequency,
                        int retryFrequency, int intervalPeriodMillisecond){
        boolean result = false;
        if (currentRetryFrequency == retryFrequency) {
            return result;
        }
        try {
            if (lock(lockKey,lockValue,seconds)) {
                log.info("获取分布式锁成功![{}]",lockKey);
                result = true;
            } else {
                log.info("等待重新获取锁[{}]",lockKey);
                TimeUnit.MILLISECONDS.sleep(intervalPeriodMillisecond);
                tryLock(lockKey,lockValue,seconds,++currentRetryFrequency, retryFrequency, intervalPeriodMillisecond);
            }
        } catch (Exception e) {
            log.error("lock异常",e);
            tryLock(lockKey,lockValue,seconds,0, 0, 0);
        }
        return result;
    }

    private boolean lock(final String lockKey,final String lockValue,final int seconds){
        return jedisTemplate.setnxex(lockKey,lockValue,seconds);
    }

    /**
     * 释放锁
     * @param lockKey
     * @param lockValue
     */
    private boolean unLock(String lockKey,String lockValue){
        //避免释放其他线程的锁
        if (lockValue.equals(jedisTemplate.get(lockKey))) {
            return jedisTemplate.del(lockKey);
        }
        return false;
    }

}
