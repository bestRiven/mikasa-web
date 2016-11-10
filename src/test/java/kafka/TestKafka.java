/*
package kafka;


import com.mikasa.core.kafka.KafkaConsumer;
import com.mikasa.core.kafka.KafkaMessage;
import com.mikasa.core.kafka.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

*/
/**
 * Created by root2 on 16/8/10.
 *//*


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-producer.xml", "classpath*:spring-consumer.xml" })
public class TestKafka {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Test
    public void testProduct(){
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setCode("name");
        kafkaMessage.setMessage("Kobe Bryant");
        kafkaProducer.sendMessage("testKafka",kafkaMessage);
    }

    @Test
    public void testConsume(){
        kafkaConsumer.processMessage();
    }
}
*/
