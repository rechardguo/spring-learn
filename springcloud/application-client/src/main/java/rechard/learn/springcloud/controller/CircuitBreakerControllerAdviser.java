package rechard.learn.springcloud.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CircuitBreakerControllerAdviser {

    @ExceptionHandler({CircuitBreakException.class})
    public String handleException(){
        System.out.println("fault in advice");
        return "fault in advice";
    }

}
