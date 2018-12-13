package rechard.learn.springboot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 *
 */
@SpringBootApplication 
@EnableWebMvc
public class Bootstrap2 {
	
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	
	public static void main(String[] args) throws Exception {
		
		SpringApplication application = new SpringApplication(Bootstrap2.class);
		Map defaultProperties = new HashMap();
		defaultProperties.put("server.port", 0);
		application.setDefaultProperties(defaultProperties);
		application.addListeners(new MyListener());
		
		application.setBannerMode(Banner.Mode.OFF);
		 // 设置为 非 web 应用
		application.setWebApplicationType(WebApplicationType.NONE);
		ConfigurableApplicationContext context =application.run(args);
		 Set<ApplicationListener<?>> set = application.getListeners();
		 for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			ApplicationListener<?> applicationListener = (ApplicationListener<?>) iterator.next();
			System.out.println("Listenr name:--->" + applicationListener);
		}
		System.out.println(context.getClass().getName());
	}
	
	static class MyListener implements ApplicationListener<ApplicationEvent>{
		public void onApplicationEvent(ApplicationEvent event) {
			System.err.println(event);
		}
	}
	
	
}
