package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rechard.learn.MyWebConfig;
import rechard.learn.bean.lifecycle.LifeCycleBean;

public class Test {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyWebConfig.class);
		 /* TranslationService myService = ctx.getBean(TranslationService.class);
		
		String msg = myService.getMessage("maker.alert", new String[]{"ะกร๗"}, Locale.TAIWAN);
		 System.out.println(msg);
		 //System.out.println(Locale.getDefault());
		 String msg2 = myService.getMessage("maker.alert", new String[]{"xiaoming"}, null);
		 System.out.println(msg2);
		 
		 ResourceBundleMessageSource ms = (ResourceBundleMessageSource)ctx.getBean("messageSource");
		 System.out.println(ms.getMessage("maker.alert", new String[]{"xiaoming"}, null));*/
		 
		/* TranslationService2 myService = ctx.getBean(TranslationService2.class);
		 String msg2 = myService.getMessage("maker.alert", new String[]{"xiaoming"}, null);
		 System.out.println(msg2);*/
		 
		 LifeCycleBean lcb = (LifeCycleBean)ctx.getBean("lifeCycle");
	}

}
