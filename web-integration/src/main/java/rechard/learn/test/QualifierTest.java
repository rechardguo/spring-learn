package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rechard.learn.ServiceConfiguration;
import rechard.learn.qualifier.UserManager;

public class QualifierTest {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext ctx = 
				 new AnnotationConfigApplicationContext(ServiceConfiguration.class);
		 UserManager userManager = ctx.getBean(UserManager.class);
		 System.out.println(userManager.getUserNameById("1"));
	}

}
