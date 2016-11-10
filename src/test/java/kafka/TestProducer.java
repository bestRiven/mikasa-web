/*
package kafka;


import com.mikasa.core.kafka.KafkaMessage;
import com.mikasa.core.kafka.Producer;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

*/
/**
 * Created by root2 on 16/8/11.
 *//*


*/
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-producer.xml"})*//*

public class TestProducer {

    */
/*@Autowired
    @Qualifier("inputToKafka")
    private MessageChannel channel;*//*

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
*/
