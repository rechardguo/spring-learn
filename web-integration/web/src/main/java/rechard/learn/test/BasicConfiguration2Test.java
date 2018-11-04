package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rechard.learn.BasicConfiguration;

public class BasicConfigurationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BasicConfiguration.class);
        context.refresh();
        Object bean = context.getBean("basicBean");
        System.out.println(bean);
    }
}
