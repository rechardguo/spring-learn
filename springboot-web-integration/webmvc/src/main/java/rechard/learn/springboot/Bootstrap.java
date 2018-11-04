package rechard.learn.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rechard.learn.springboot.bean.User;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class Bootstrap {
	
	@Autowired
	User user;
	
	@RequestMapping("/user")
	User home() {
		return user;
	}
	
	public static void main(String[] args) throws Exception {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		
		
		
		context.setId("rechard context");
		context.registerBean("mybean", User.class, "xiaoming",20);
		context.refresh();
		new SpringApplicationBuilder(Bootstrap.class)
		.parent(context)
		// 增加一个事件监听器
		.listeners(new ApplicationListener<ApplicationEvent>() {
			public void onApplicationEvent(ApplicationEvent event) {
				System.err.println("监听到事件----->"+event);
			}
		})
		.properties("server.port=8888")
		.run(args);
		System.out.println("-------------------------------------------");
		
		
		//context.close();
	}
}
