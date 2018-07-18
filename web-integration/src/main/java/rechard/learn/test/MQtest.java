package rechard.learn.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rechard.learn.ServiceConfiguration;
import rechard.learn.mq.DeliveryJMSHandler;

public class MQtest {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext ctx = 
				 new AnnotationConfigApplicationContext(ServiceConfiguration.class);
		 /*LifeCycleBean lcb = (LifeCycleBean)ctx.getBean("lifeCycle");*/
		 DeliveryJMSHandler sender = (DeliveryJMSHandler)ctx.getBean(DeliveryJMSHandler.class);
		 sender.send("234234", "3434", "343434", "33333");
		 sender.send2("234234", "3434", "343434", "33333");
	}

}
