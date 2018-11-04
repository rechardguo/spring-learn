package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rechard.learn.BasicConfiguration;

public class BasicConfigurationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BasicConfiguration.class);
        context.refresh();

        //BasicConfiguration -> @Bean()
        Object bean = context.getBean("basicBean");
        System.out.println(bean);

        //BasicConfiguration -> @Bean(name = { "beanName1", "beanName2", "beanName3" })
       /* Object bean1 = context.getBean("beanName1");
        System.out.println(bean1);
        Object bean2 = context.getBean("beanName2");
        System.out.println(bean2);*/
    }
}
