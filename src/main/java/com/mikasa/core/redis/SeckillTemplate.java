package com.mikasa.core.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by zhangyu on 16/8/9.
 */

@Service
public class SeckillTemplate {

    Logger log = LoggerFactory.getLogger(SeckillTemplate.class);

    @Autowired
    private JedisTemplate jedisTemplate;

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private static final String prefix = "secondKillUser";

    private static final String lockPrefix = "secondKillLock";

    private static final int seckillNum = 4; //秒杀数量

    private static ExecutorService threadPool = Executors.newCachedThreadPool();


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
            Long maxSize = jedisTemplate.llen(prefix);
            if(maxSize > seckillNum){
                List<String> list = new ArrayList<String>();
                for(int i = 0;i<maxSize;i++){
                    list.add(jedisTemplate.rpop(prefix));
                }
                //TODO 为用户分配库存 下订单

                map.put("result","已经秒杀完毕"+list);
            }else {
                if(jedisTemplate.exists("check"+userId)){
                    map.put("result","每个用户只能秒杀一件"+userId);
                }else {
                    try {
                        Long timestamp = System.currentTimeMillis();
                        /**
                         * 每隔0.5秒尝试一次获得锁，总共60次，也就是30秒仍旧未获得锁，则直接return
                         */
                        if(lock(lockPrefix+userId,60,0,60,500)){
                            jedisTemplate.lpush(prefix,userId);
                            jedisTemplate.set("check"+userId,userId+timestamp);
                            map.put("result","秒杀成功:"+jedisTemplate.lrangeAll(prefix));
                        }else {
                            map.put("result","秒杀失败:"+userId);
                        }

                    }finally {
                        unLock(lockPrefix+userId);
                    }

                }
            }
            return map;
        }
    }

    /**
     * 实现分布式锁
     * @param lockName
     * @param seconds 超时时间
     * @param currentRetryFrequency
     * @param retryFrequency 重试时间
     * @param intervalPeriodMillisecond 重试间隔
     */
    public boolean lock(String lockName,int seconds,int currentRetryFrequency,
                        int retryFrequency, int intervalPeriodMillisecond){
        boolean result = false;
        if (currentRetryFrequency == retryFrequency) {
            return result;
        }
        try {
            if (tryLock(lockName,seconds)) {
                log.info("获取分布式锁成功![{}]",lockName);
                result = true;
            } else {
                log.info("等待重新获取锁[{}]",lockName);
                TimeUnit.MILLISECONDS.sleep(intervalPeriodMillisecond);
                lock(lockName,seconds,++currentRetryFrequency, retryFrequency, intervalPeriodMillisecond);
            }
        } catch (Exception e) {
            log.error("lock异常",e);
            lock(lockName,seconds,0, 0, 0);
        }
        return result;
    }

    private boolean tryLock(final String lockName,final int seconds){
        return jedisTemplate.setnxex(lockName,lockName,seconds);
    }

    /**
     * 释放锁
     * @param lockName
     */
    private void unLock(String lockName){
        jedisTemplate.del(lockName);
    }

}
