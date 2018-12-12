package rechard.learn.springbatch.sample.rmi.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.rmi.RmiInboundGateway;
import org.springframework.integration.security.channel.ChannelSecurityInterceptor;
import org.springframework.integration.security.channel.SecuredChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder =new SpringApplicationBuilder(ServerApplication.class);
        builder.run(args);
    }

    @Bean
    public RmiInboundGateway inbound(@Qualifier("rmi-input") MessageChannel channel) {
        RmiInboundGateway gateway = new RmiInboundGateway();
        gateway.setRequestChannel(channel);
        gateway.setRegistryPort(6666);
        return gateway;
    }

    @Bean("rmi-input")
    public MessageChannel requestChannel() {
        PublishSubscribeChannel channel = new PublishSubscribeChannel();
        return channel;
    }
}
