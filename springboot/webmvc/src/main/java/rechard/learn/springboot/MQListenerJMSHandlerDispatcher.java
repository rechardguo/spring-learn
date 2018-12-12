package rechard.learn.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import rechard.learn.springboot.entity.Base;
import rechard.learn.springboot.jms.handler.JMSListenerHandler;
import rechard.learn.springboot.stereotype.JMSHandler;

import java.util.Map;

@Service
public class MQListenerJMSHandlerDispatcher implements ApplicationContextAware{

    private ApplicationContext context;
    public void process(Object payload){
        Map<String, JMSListenerHandler> msp =BeanFactoryUtils.beansOfTypeIncludingAncestors(context,JMSListenerHandler.class);
        try {
            msp.values().stream().filter(handler->{
                 JMSHandler[] jmsHandlers =  handler.getClass().getAnnotationsByType(JMSHandler.class);
                 JMSHandler jmsHandler =jmsHandlers[0];
                 Class payloadClass = jmsHandler.jmsPayLoad();
                 RequestType choiceType = jmsHandler.requestType();
                 Base base =(Base)payload;
                 RequestType requestType = RequestType.valueOf(base.getRequestType());
                 return base.getClass().isAssignableFrom(payloadClass) &&
                         requestType==choiceType;
             }).findFirst().get().process(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}
