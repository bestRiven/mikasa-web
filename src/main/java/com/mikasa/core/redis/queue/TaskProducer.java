package com.mikasa.core.redis.queue;

import com.mikasa.core.redis.JedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.UUID;

/**
 * Created by sherlock on 2016/12/7.
 */
public class TaskProducer implements Runnable{

    @Autowired
    private JedisTemplate jedisTemplate;

    @Override
    public void run() {
        Random random = new Random();
        while(true){
            try{
                Thread.sleep(random.nextInt(600) + 600);
                // 模拟生成一个任务
                UUID taskid = UUID.randomUUID();
                //将任务插入任务队列：task-queue
                jedisTemplate.lpush("task-queue", taskid.toString());
                System.out.println("插入了一个新的任务： " + taskid);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
