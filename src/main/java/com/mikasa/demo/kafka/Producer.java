package com.mikasa.demo.kafka;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.Random;

/**
 * Created by root2 on 16/7/28.
 */
public class Producer {

    private static final String CONFIG = "/spring-producer.xml";
    private static Random rand = new Random();

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer.class);
        ctx.start();
        final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);
        /*for (int i = 0; i < 100; i++) {
            channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", String.valueOf(i)).setHeader(KafkaHeaders.TOPIC, "test").build());
        }*/
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setCode("name");
        kafkaMessage.setMessage("Kobe Bryant");
        channel.send(MessageBuilder.withPayload(kafkaMessage).setHeader("messageKey", "testKafka").setHeader(KafkaHeaders.TOPIC, "test").build());
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

}
