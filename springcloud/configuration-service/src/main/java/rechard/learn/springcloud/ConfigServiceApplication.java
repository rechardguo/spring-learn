package rechard.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }


  /*  *//**
     * 替代掉git方式的获取
     * @return
     *//*
    @Bean
    EnvironmentRepository myEnvironmentRepository(){
        EnvironmentRepository repository =(String application, String profile, String label)->{
            Environment environment = new Environment(application, profile, label, (String)null, (String)null);
            Map map = new HashMap<>();
            map.put("message","my customer message hello wolrd");
            PropertySource propertySource = new PropertySource("myEnvironmentRepository",map);
            environment.add(propertySource);
            return environment;
        };
        return repository;
    }*/
}
