package rechard.learn.springbatch.sample.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class HelloConfiguration{

    @MessagingGateway
    public interface  GW{
        @Gateway(requestChannel = "input")
        public String greeting(String name);
    }

    @Bean("input")
    public MessageChannel psChannel(){
        return new PublishSubscribeChannel();
    }
}
