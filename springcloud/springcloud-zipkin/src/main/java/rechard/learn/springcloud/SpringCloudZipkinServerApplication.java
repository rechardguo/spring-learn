package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SpringCloudZipkinServerApplication{
    public static void main( String[] args )    {
        SpringApplication.run(SpringCloudZipkinServerApplication.class, args);
    }
}
