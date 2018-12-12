package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Configuration
@SpringBootApplication
public class GatewayApplication{


    public static void main( String[] args ){

        SpringApplication.run(GatewayApplication.class, args);
    }
}
