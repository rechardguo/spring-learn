package rechard.learn.springbatch.sample.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;

import javax.annotation.PostConstruct;

@Configuration
public class SubscriberConfiguration {

    @Autowired
    @Qualifier("input")
    PublishSubscribeChannel channel;

    @PostConstruct
    public void addSubsrciber(){
        this.channel.subscribe((msg)->{
            System.err.println("message handler->"+msg.getPayload());
        });
    }
}
