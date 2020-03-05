package com.example.demo.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
//@Component
public class ValidationExceptionAspect {


   /* @AfterThrowing(
            //pointcut="org.springframework.web.method..ModelAttributeMethodProcessor.resolveArgument()",
            pointcut="org.springframework.web.method.annotation.ModelAttributeMethodProcessor.resolveArgument()",
            throwing="ex")
    public ModelAndView handle(BindException ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",ex.getBindingResult().getTarget());
        modelAndView.addObject( BindingResult.MODEL_KEY_PREFIX + "user",ex);
        modelAndView.setViewName("index");
        return modelAndView;
    }*/


    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getArgs());
        return pjp.proceed();
    }

    @Pointcut("execution(* org.springframework.web.method.annotation.ModelAttributeMethodProcessor.supportsParameter(*))")
    public void methodsToBeProfiled(){

    }
}
