package rechard.learn;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;


/**
 * 
 * @author Rechard
 *
 *cmd | runmqsc CBMQ | alter qmgr chlauth(disabled)
ALTER AUTHINFO(SYSTEM.DEFAULT.AUTHINFO.IDPWOS) AUTHTYPE(IDPWOS) CHCKCLNT(OPTIONAL)
REFRESH SECURITY TYPE(CONNAUTH)
 */

// refer to https://github.com/lzp4ever/IBM_WebSphere_MQ_Spring_Boot_JMS
@Configuration
@ComponentScan(basePackages="rechard.learn.mq")
@PropertySource("classpath:mq.properties")
public class MQ2Configuration {
	@Value("${spring.mq.host}")
	private String host;
	@Value("${spring.mq.port}")
	private int port;
	@Value("${spring.mq.queue-manager}")
	private String queueManager;	
	@Value("${spring.mq.channel}")	
	private String channel;
	@Value("${spring.mq.ccsid}")
	private int ccsid;
	@Value("${spring.mq.receive-timeout}")
	private int receiveTimeout;
	
	
	@Bean
	public MQQueueConnectionFactory mq2QueueConnectionFactory() {
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
	    cachingConnectionFactory(@Qualifier("mq2QueueConnectionFactory")MQQueueConnectionFactory mqQueueConnectionFactory) {
	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
	    cachingConnectionFactory.setTargetConnectionFactory(mqQueueConnectionFactory);
	    cachingConnectionFactory.setSessionCacheSize(500);
	    cachingConnectionFactory.setReconnectOnException(true);
	    return cachingConnectionFactory;
	}
	
	@Bean
	public JmsOperations jms2Operations(CachingConnectionFactory cachingConnectionFactory) {
	    JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
	    jmsTemplate.setReceiveTimeout(receiveTimeout);
	    return jmsTemplate;
	}

}
