package com.mikasa.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root2 on 16/7/28.
 */
public class Consumer {

    private static final String CONFIG = "/spring-consumer.xml";
    private static Random rand = new Random();

    @SuppressWarnings({ "unchecked", "unchecked", "rawtypes" })
    public static void main(String[] args) {

        Logger log = LoggerFactory.getLogger(Consumer.class);

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Consumer.class);
        ctx.start();
        final QueueChannel channel = ctx.getBean("inputFromKafka", QueueChannel.class);
        Message msg;
        while((msg = channel.receive()) != null) {
            HashMap map = (HashMap)msg.getPayload();
            Set<Map.Entry> set = map.entrySet();
            for (Map.Entry entry : set) {
                String topic = (String)entry.getKey();
                System.out.println("Topic:" + topic);
                ConcurrentHashMap<Integer,List<byte[]>> messages = (ConcurrentHashMap<Integer,List<byte[]>>)entry.getValue();
                Collection<List<byte[]>> values = messages.values();
                for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
                    List<byte[]> list = iterator.next();
                    for (byte[] object : list) {
                        String message = new String(object);
                        System.out.println("\tMessage: " + message);
                    }

                }

            }

        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

}
