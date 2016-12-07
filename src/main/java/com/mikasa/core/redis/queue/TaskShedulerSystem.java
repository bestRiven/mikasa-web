package com.mikasa.core.redis.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sherlock on 2016/12/7.
 */
public class TaskShedulerSystem {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {

        // 启动一个生产者线程，模拟任务的产生

        new Thread(new TaskProducer()).start();
        //threadPool.execute(new TaskProducer());
        Thread.sleep(15000);

        //启动一个线程者线程，模拟任务的处理
        new Thread(new TaskConsumer()).start();

        //主线程休眠
        Thread.sleep(Integer.MAX_VALUE);
    }
}
