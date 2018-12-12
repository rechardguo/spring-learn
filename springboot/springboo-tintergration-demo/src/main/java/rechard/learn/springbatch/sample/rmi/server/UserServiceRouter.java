package rechard.learn.springbatch.sample.rmi.server;

import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class UserServiceRouter {

    @Router(inputChannel = "rmi-input")
    public String route(Message msg){
        System.out.println("msg payload : "+msg.getPayload());
        System.out.println("msg method : "+msg.getHeaders().get("method"));
        return msg.getHeaders().get("method").toString();
    }
}
