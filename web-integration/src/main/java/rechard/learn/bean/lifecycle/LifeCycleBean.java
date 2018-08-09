package rechard.learn.bean.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LifeCycleBean implements InitializingBean ,DisposableBean ,BeanPostProcessor,BeanNameAware {


	public LifeCycleBean(){
		System.out.println("Constructor");
	}
	
	
	//It is recommended that you do not use the InitializingBean interface because it unnecessarily couples the code to Spring. 
	//Alternatively, use the @PostConstruct annotation or specify a POJO initialization method
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean ->afterPropertiesSet()");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean ->destroy()");
	}


	@PostConstruct
	public void postConstruct() {
		System.out.println("PostConstruct");
	}

	@PreDestroy
	public void preDestory() {
		System.out.println("PreDestroy");
	}
	
	
	public void myinit(){
		System.out.println("myinit");
	}
	
	public void mydestroy(){
		System.out.println("mydestroy");
	}
	
	
	public void hello(){
		System.out.println("life cycle bean hello world!!!!");
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println(arg0);
		System.out.println("BeanPostProcessor->postProcessAfterInitialization()");
		System.out.println("-------------------------------------------------");
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("BeanPostProcessor->postProcessBeforeInitialization()");
		return arg0;
	}


	@Override
	public void setBeanName(String name) {
		System.out.println("bean name aware : "+name);
	}

}
