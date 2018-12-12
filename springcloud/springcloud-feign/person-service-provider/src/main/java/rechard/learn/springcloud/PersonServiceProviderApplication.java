package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import rechard.learn.springcloud.bean.Person;
import rechard.learn.springcloud.web.WebMvcConfig;


@SpringBootApplication
//@EnableHystrix
//@EnableDiscoveryClient
@Import(WebMvcConfig.class)
@EnableBinding(Sink.class)
public class PersonServiceProviderApplication{
    public static void main( String[] args ){
        SpringApplication.run(PersonServiceProviderApplication.class,args);
    }

    @StreamListener(Sink.INPUT)
    public void handle(String str) {
        System.out.println("Received: " + str);
    }
}
