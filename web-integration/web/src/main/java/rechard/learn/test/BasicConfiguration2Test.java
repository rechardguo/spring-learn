package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rechard.learn.BasicConfiguration;
import rechard.learn.BasicConfiguration2;
import rechard.learn.bean.Foo;

public class BasicConfiguration2Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BasicConfiguration.class, BasicConfiguration2.class);
        context.refresh();
        Object bean = context.getBean("str1");
        System.out.println(bean);
        Object bean2 = context.getBean("str2");
        System.out.println(bean2);
        Object basicBean = context.getBean("basicBean");
        System.out.println(basicBean);

        Foo foo = (Foo)context.getBean("foo");
        System.out.println(foo.hi());
    }
}
