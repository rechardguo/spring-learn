package rechard.learn.spring.webintegration.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mq.properties")
public class DeliveryJMSHandler {
	public enum DeliveryMode {
		EMAIL("EMAIL"), FAX("FAX"), SMS("SMS");

		private String code;

		DeliveryMode(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}
	}

	@Autowired
	JmsOperations jmsOperations;

	@Value("${ssm.delivery.queue}")
	private String DELIVERY_QUEUE_NAME;

	public void send(String contact, String subject, String content,String mode) {
			jmsOperations.convertAndSend(DELIVERY_QUEUE_NAME, "1334234234");
	}
	
	
	@Autowired
	@Qualifier("jms2Operations")
	JmsOperations jms2Operations;
	
	public void send2(String contact, String subject, String content,String mode) {
		jms2Operations.convertAndSend(DELIVERY_QUEUE_NAME, "1334234234");
}
}
