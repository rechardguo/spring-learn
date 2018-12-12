package rechard.learn.springcloud.kafka.springcloud.binder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableBinding(Sink.class)
public class MessageConsumerBean {

    @Autowired
    @Qualifier(Sink.INPUT)
    SubscribableChannel inputChannel;

    @StreamListener(Sink.INPUT)
    public void handle(String message) {
        System.err.println("sink Received: " + message);
    }

    @PostConstruct
    public void setHandler(){
        this.inputChannel.subscribe(message->{
            System.err.println("@PostConstruct Received: " + message.getPayload());
        });
    }


    //通过@ServiceActivator
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Object message) {
        System.out.println("@ServiceActivator : " + message);
    }



   /* public static void main(String[] args) {
        Byte b = new Byte("1");
        System.out.println(b);
    }*/
}
