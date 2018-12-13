package rechard.learn.springbatch.sample.rmi.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.rmi.RmiOutboundGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import rechard.learn.springbatch.sample.rmi.server.User;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder =new SpringApplicationBuilder(ClientApplication.class);
        ConfigurableApplicationContext context = builder.run(args);
        RmiOutboundGateway gw = (RmiOutboundGateway)context.getBean("outbound");
        Message<User> msg =MessageBuilder.withPayload(new User(1,"rechard"))
                .setHeader("method","addUser")
                .build();
        gw.handleRequestMessage(msg);
///////////////////////////////////////////////////////////////////
        Message<Integer> msg2 =MessageBuilder.withPayload(1)
                .setHeader("method","findUserById")
                .build();
        Object obj = gw.handleRequestMessage(msg2);
        System.out.println("return obj:"+obj);
    }

    @Bean
    @ServiceActivator(inputChannel="inChannel")
    public RmiOutboundGateway outbound() {
        RmiOutboundGateway gateway = new RmiOutboundGateway("rmi://127.0.0.1:6666/org.springframework.integration.rmiGateway.rmi-input");
        return gateway;
    }
}
