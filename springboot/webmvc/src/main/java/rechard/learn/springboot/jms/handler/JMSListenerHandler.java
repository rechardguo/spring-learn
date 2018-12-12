package rechard.learn.springboot.jms.handler;

public interface JMSListenerHandler {

    public void process (Object msg) throws  Exception;
}
