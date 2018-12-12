package rechard.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.springcloud.kafka.springcloud.binder.MessageProducerBean;

@RestController
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    @Autowired
    private MessageProducerBean messageProducerBean;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @PostMapping("/message/send")
    public Boolean sendMessage(@RequestParam String message) {
        kafkaTemplate.send(topic, message);
        return true;
    }

    @PostMapping("/sc/binder/message/send")
    public Boolean scBinderSendMessage(@RequestParam String message) {
       return messageProducerBean.sendMessage(message);
    }

}
