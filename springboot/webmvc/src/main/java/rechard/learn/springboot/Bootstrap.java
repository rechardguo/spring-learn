package rechard.learn.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplicationBuilder builder=new SpringApplicationBuilder(Bootstrap.class);
        builder.run(args);
    }
}
