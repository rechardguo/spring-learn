package rechard.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class KTBootStrap {

    public static void main(String[] args) {
        SpringApplication application= new SpringApplication(KTBootStrap.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
    }
}
