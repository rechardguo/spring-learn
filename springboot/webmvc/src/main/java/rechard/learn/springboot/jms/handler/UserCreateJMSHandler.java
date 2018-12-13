package rechard.learn.springboot.jms.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rechard.learn.springboot.RequestType;
import rechard.learn.springboot.entity.User;
import rechard.learn.springboot.stereotype.JMSHandler;

@JMSHandler(jmsPayLoad = User.class, requestType = RequestType.CREATE_USER)
public class UserCreateJMSHandler implements JMSListenerHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public void process(Object msg) throws Exception {
        logger.info("create user : "+msg.toString());
    }
}
