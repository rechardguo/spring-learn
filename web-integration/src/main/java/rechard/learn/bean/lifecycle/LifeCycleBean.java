package rechard.learn.bean.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


public class LifeCycleBean implements InitializingBean ,DisposableBean ,BeanPostProcessor{

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
	public void populateMovieCache() {
		System.out.println("populateMovieCache()");
	}

	@PreDestroy
	public void clearMovieCache() {
		System.out.println("clearMovieCache()");
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		return null;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		return null;
	}

}
