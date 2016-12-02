package com.mikasa.service;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.mikasa.core.redis.JedisTemplate;
import com.mikasa.model.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sherlock on 2016/12/2.
 */

@Service
public class RedisSeckillService {

    Logger logger = LoggerFactory.getLogger(RedisSeckillService.class);

    @Autowired
    private JedisTemplate jedisTemplate;

    //Serialize function
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        //redis operate
        try {
            String key = "seckill:" + seckillId;
            //由于redis内部没有实现序列化方法,而且jdk自带的implaments Serializable比较慢,会影响并发,因此需要使用第三方序列化方法.
            byte[] bytes = jedisTemplate.get(key.getBytes());
            if(null != bytes){
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                //reSerialize
                return seckill;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return null;
    }


    public void putSeckill(Seckill seckill) {
        //set Object(seckill) ->Serialize -> byte[]
        try{
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //time out  cache
                int timeout = 60*60;
                jedisTemplate.setex(key.getBytes(),bytes,timeout);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
