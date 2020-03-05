package com.example.demo.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ValidationExceptionAdvice {

    @ExceptionHandler
    public ModelAndView handle(BindException ex,HandlerMethod handlerMethod){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",ex.getBindingResult().getTarget());
        modelAndView.addObject( BindingResult.MODEL_KEY_PREFIX + "user",ex);
        modelAndView.setViewName("index");
        return modelAndView;

    }
}
