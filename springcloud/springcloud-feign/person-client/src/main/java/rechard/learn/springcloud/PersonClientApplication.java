package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import rechard.learn.springcloud.service.PersonService;
import rechard.learn.springcloud.web.WebMvcConfig;


@SpringBootApplication
@EnableFeignClients(clients={PersonService.class})
@EnableDiscoveryClient //能够被注册中心发现
@Import(WebMvcConfig.class)
public class PersonClientApplication
{
    public static void main( String[] args )
    {

        SpringApplication.run(PersonClientApplication.class, args);
    }

}
