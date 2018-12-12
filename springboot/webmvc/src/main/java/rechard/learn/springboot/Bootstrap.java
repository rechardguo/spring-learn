package rechard.learn.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplicationBuilder builder=new SpringApplicationBuilder(Bootstrap.class);
        builder.run(args);
    }
}
