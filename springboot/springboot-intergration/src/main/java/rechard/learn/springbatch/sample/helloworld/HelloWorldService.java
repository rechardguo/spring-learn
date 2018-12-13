package rechard.learn.springbatch.sample.helloworld;

import org.springframework.integration.annotation.EndpointId;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @EndpointId("existHelloWorldService")
    @ServiceActivator(inputChannel ="input")
    public void greeting(String name){
        System.err.println("HelloWorldServicce:hello world,"+name);
    }
}
