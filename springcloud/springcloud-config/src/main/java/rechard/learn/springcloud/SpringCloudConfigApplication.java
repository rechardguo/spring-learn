package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Profile;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigApplication
{
    public static void main( String[] args )
    {
        SpringApplication springApplication = new SpringApplication(SpringCloudConfigApplication.class);
       // springApplication.setAdditionalProfiles("default");
        springApplication.run(args);
       // SpringApplication.run(ConfigApplication.class, args);
    }
}
