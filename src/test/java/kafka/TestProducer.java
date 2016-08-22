package kafka;

import com.zy.kafka.KafkaMessage;
import com.zy.kafka.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root2 on 16/8/11.
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-producer.xml"})*/
public class TestProducer {

    /*@Autowired
    @Qualifier("inputToKafka")
    private MessageChannel channel;*/
    private static final String CONFIG = "/spring-producer.xml";

    @Test
    public void testProduct(){
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer.class);
        ctx.start();
        final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setCode("name");
        kafkaMessage.setMessage("Kobe Bryant");
        channel.send(MessageBuilder.withPayload(kafkaMessage).setHeader("messageKey", "testKafka").setHeader(KafkaHeaders.TOPIC, "test").build());
    }
}
