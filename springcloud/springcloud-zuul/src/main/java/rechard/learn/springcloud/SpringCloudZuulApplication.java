package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //它默认加上了@EnableCircuitBreaker和@EnableDiscoveryClient
public class SpringCloudZuulApplication {
    public static void main( String[] args ){
        SpringApplication.run(SpringCloudZuulApplication.class, args);
    }
}
