package rechard.learn.springbatch.sample.helloworld;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class GreetingMessageEndpoint {

    @ServiceActivator(inputChannel="input")
    public void handle(String str){
        System.err.printf("receive message-> %s",str);
    }

}
