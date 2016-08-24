package com.mikasa.core.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Created by root2 on 16/8/10.
 */

@Service
public class KafkaProducer {

    static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    @Qualifier("inputToKafka")
    private MessageChannel channel;

    public void sendMessage(String key, KafkaMessage kafkaMessage) {
        /*Message msg = MessageBuilder.withPayload(kafkaMessage)
                .setHeader("kafkaMessage", key)
                .setHeader(KafkaHeaders.TOPIC, "test").build();
        channel.send(msg);
        logger.info("消息生产者发送消息"+msg);*/
        channel.send(MessageBuilder.withPayload(kafkaMessage)
                .setHeader("messageKey", key)
                .setHeader(KafkaHeaders.TOPIC, "test").build());
        //logger.info("消息生产者发送消息"+msg);
    }
}
