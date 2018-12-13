package rechard.learn.springbatch.sample.helloworld;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class HelloApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(HelloApplication.class);
        HelloConfiguration.GW gw = context.getBean(HelloConfiguration.GW.class);
        gw.greeting("rechard");
        context.close();
    }
}
