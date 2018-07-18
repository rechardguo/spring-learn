package rechard.learn.test;

import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rechard.learn.MQ2Configuration;
import rechard.learn.MQConfiguration;
import rechard.learn.ServiceConfiguration;
import rechard.learn.mq.DeliveryJMSHandler;
import rechard.learn.service.TranslationService;

public class TranslationTest {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext ctx = 
				 new AnnotationConfigApplicationContext(ServiceConfiguration.class);
		 TranslationService myService = ctx.getBean(TranslationService.class);
		
		String msg = myService.getMessage("maker.alert", new String[]{"ะกร๗"}, Locale.SIMPLIFIED_CHINESE);
		 System.out.println(msg);
		 String msg2 = myService.getMessage("maker.alert", new String[]{"xiaoming"}, null);
		 System.out.println(msg2);
		
	}

}
