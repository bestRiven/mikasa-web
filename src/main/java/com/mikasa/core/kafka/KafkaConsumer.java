package com.mikasa.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root2 on 16/8/10.
 */
public class KafkaConsumer {

    static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    @Qualifier("inputFromKafka")
    private QueueChannel channel;

    private Message msg;

    public void processMessage() {
        logger.info("消费者消费开始...");
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
    }
}
