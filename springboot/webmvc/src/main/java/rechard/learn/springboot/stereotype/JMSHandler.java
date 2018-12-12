package rechard.learn.springboot.stereotype;

import org.springframework.stereotype.Component;
import rechard.learn.springboot.RequestType;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface JMSHandler {
    Class jmsPayLoad();
    RequestType requestType();
}
