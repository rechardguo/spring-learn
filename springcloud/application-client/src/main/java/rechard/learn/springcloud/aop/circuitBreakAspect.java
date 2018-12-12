package rechard.learn.springcloud.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import rechard.learn.springcloud.annotation.CircuitBreake;
import rechard.learn.springcloud.controller.CircuitBreakException;

import java.util.Random;
import java.util.concurrent.*;

@Aspect
@Component
public class circuitBreakAspect {

    private final Random random =new Random();

    @Pointcut("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall(..))")
    public void myAspectCallPointCut(){
    }

    @Pointcut("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall2(..))")
    public void myAspectCallPointCut2(){
    }

    @Pointcut("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall3(..))")
    public void myAspectCallPointCut3(){
    }

    @AfterThrowing(pointcut="myAspectCallPointCut()",throwing="ex")
    public String handleException(Exception ex)throws CircuitBreakException{
        if(ex instanceof  TimeoutException)
            throw new CircuitBreakException("熔断",ex);
        return circuitBreakerCall();
    }

    @AfterThrowing(pointcut="myAspectCallPointCut2()",throwing="ex")
    public String handleException2(Exception ex)throws CircuitBreakException{
        if(ex instanceof  TimeoutException)
            throw new CircuitBreakException("熔断",ex);
        return circuitBreakerCall();
    }

    @AfterThrowing(pointcut="myAspectCallPointCut3()",throwing="ex")
    public String handleException3(Exception ex)throws CircuitBreakException{
        if(ex instanceof  TimeoutException)
            throw new CircuitBreakException("熔断",ex);
        return circuitBreakerCall();
    }

    @Around("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall2(..))" +
            "&&  @annotation(circuitBreak)")
    public String doHandle2(ProceedingJoinPoint pjp,
                            CircuitBreake circuitBreak)throws Exception {
        return doInvoke(pjp,circuitBreak.value());
    }


    @Around("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall(..))")
    public String doHandle(ProceedingJoinPoint pjp)throws Exception {
        return doInvoke(pjp,100);
    }

    @Around("execution(* rechard.learn.springcloud.controller.CircuitBreakerController.myAspectCall3(..))")
    public String doHandle3(ProceedingJoinPoint pjp)throws Exception {
        if(pjp instanceof MethodInvocationProceedingJoinPoint){
            MethodInvocationProceedingJoinPoint mpjp = (MethodInvocationProceedingJoinPoint)pjp;
            MethodSignature msi =
                    (MethodSignature)mpjp.getSignature();
            CircuitBreake circuitBreake = msi.getMethod().getAnnotation(CircuitBreake.class);
            return doInvoke(pjp,circuitBreake.value());
        }
        return null;
    }


    public String circuitBreakerCall(){
        return "other exception fault!!!";
    }

    private String doInvoke(ProceedingJoinPoint pjp,long timeout) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(()->{
            String retVal = null;
            try {
                retVal = (String)pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return retVal;
        });
        String result = null;
        try {
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            future.cancel(true);
            throw e;
        }
        return result;
    }
}
