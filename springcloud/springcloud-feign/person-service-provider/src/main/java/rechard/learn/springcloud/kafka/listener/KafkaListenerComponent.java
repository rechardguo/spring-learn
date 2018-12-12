package rechard.learn.springcloud.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerComponent {

    /*@KafkaListener(topics ="${kafka.topic}",groupId = "${kafka.consumer.group1}")
    public void onMessage(String message) {
        System.out.println("Kafka 消费者1监听器，接受到消息：" + message);
    }

    @KafkaListener(topics ="${kafka.topic}",groupId = "${kafka.consumer.group2}")
    public void onMessage2(String message) {
        System.out.println("Kafka 消费者2监听器，接受到消息：" + message);
    }*/

}
