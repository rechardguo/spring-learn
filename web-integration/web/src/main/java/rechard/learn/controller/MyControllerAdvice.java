package rechard.learn.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler({NoHandlerFoundException.class,NullPointerException.class})
    public String  handle(ServletRequest request, ServletResponse response) {
        return "redirect:error";
    }
}