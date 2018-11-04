package rechard.learn.spring.webintegration.mq;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

// refer to https://github.com/lzp4ever/IBM_WebSphere_MQ_Spring_Boot_JMS
@Configuration
@ComponentScan(basePackages="rechard.learn.spring.webintegration.mq")
@PropertySource("classpath:/mq.properties")
public class MQConfiguration {
	@Value("${delivery.spring.mq.host}")
	private String host;
	@Value("${delivery.spring.mq.port}")
	private int port;
	@Value("${delivery.spring.mq.queue-manager}")
	private String queueManager;	
	@Value("${delivery.spring.mq.channel}")	
	private String channel;
	@Value("${delivery.spring.mq.ccsid}")
	private int ccsid;
	@Value("${delivery.spring.mq.receive-timeout}")
	private int receiveTimeout;
	
	
	@Bean
	public MQQueueConnectionFactory mqQueueConnectionFactory() {
	    MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
	    mqQueueConnectionFactory.setHostName(host);
	    try {
	        mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
	        mqQueueConnectionFactory.setCCSID(ccsid);
	        mqQueueConnectionFactory.setPort(port);
	        mqQueueConnectionFactory.setChannel(channel);
	        mqQueueConnectionFactory.setQueueManager(queueManager);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return mqQueueConnectionFactory;
	}
	
	@Bean
	public CachingConnectionFactory 
	    deliverycachingConnectionFactory(MQQueueConnectionFactory mqQueueConnectionFactory) {
	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
	    cachingConnectionFactory.setTargetConnectionFactory(mqQueueConnectionFactory);
	    cachingConnectionFactory.setSessionCacheSize(500);
	    cachingConnectionFactory.setReconnectOnException(true);
	    return cachingConnectionFactory;
	}
	
	@Bean
	public JmsOperations jmsOperations(@Qualifier("deliverycachingConnectionFactory")CachingConnectionFactory cachingConnectionFactory) {
	    JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
	    jmsTemplate.setReceiveTimeout(receiveTimeout);
	    return jmsTemplate;
	}
	

	//@Bean
	//for spring boot , it can be configure in application.properties with prefix spring.messages
	//refer MessageSourceAutoConfiguration
	//spring.messages.basename
	/*public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
		messageSource.addBasenames("alert","messages");
		return messageSource;
	}*/
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("alert");
		return messageSource;
	}

}
