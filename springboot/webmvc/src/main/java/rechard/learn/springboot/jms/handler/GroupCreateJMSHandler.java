package rechard.learn.springboot.jms.handler;

import rechard.learn.springboot.RequestType;
import rechard.learn.springboot.entity.User;
import rechard.learn.springboot.stereotype.JMSHandler;

@JMSHandler(jmsPayLoad = User.class, requestType = RequestType.CREATE_USER)
public class GroupCreateJMSHandler implements JMSListenerHandler {
    public void process(Object msg) throws Exception {
        System.out.println("create user : "+msg.toString());
    }
}
