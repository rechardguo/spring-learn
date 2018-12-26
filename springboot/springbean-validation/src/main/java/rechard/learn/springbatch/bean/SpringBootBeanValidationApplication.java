package rechard.learn.springbatch.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootBeanValidationApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBeanValidationApplication.class, args);
    }

    /*public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserControllerInterceptor());
    }*/
}
