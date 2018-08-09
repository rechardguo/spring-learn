package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rechard.learn.BasicConfiguration;
import rechard.learn.bean.Foo;

public class QualifierTest {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext ctx = 
				 new AnnotationConfigApplicationContext(BasicConfiguration.class);
		 Foo foo = ctx.getBean(Foo.class);
		 System.out.println(foo.hi());
	}

}
