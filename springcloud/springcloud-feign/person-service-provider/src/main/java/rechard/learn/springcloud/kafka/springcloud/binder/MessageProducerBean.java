package rechard.learn.springcloud.kafka.springcloud.binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MessageProducerBean {

    @Autowired
    @Qualifier(Source.OUTPUT) // Bean 名称
    private MessageChannel  channel;

    public boolean sendMessage(String message){
        Message<String> msg = new GenericMessage<String>(message);

        return this.channel.send(msg);
    }
}
