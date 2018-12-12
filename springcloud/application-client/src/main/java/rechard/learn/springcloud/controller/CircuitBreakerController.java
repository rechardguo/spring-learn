package rechard.learn.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandBuilder;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.springcloud.annotation.CircuitBreake;

import java.util.Random;
import java.util.concurrent.*;

@RestController
public class CircuitBreakerController {


    private Random random = new Random();

    private static final int MAX_AVAILABLE = 2;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    @Value("${remote.call.timeout:100}")
    private long timout ;

    @RequestMapping("/cb/aspect/my/call")
    public String myAspectCall() throws Exception{
        return doCall();
    }

    @RequestMapping("/cb/aspect/my/call2")
    @CircuitBreake(300)
    public String myAspectCall2() throws Exception{
     return doCall();
    }

    @RequestMapping("/cb/aspect/my/call3")
    @CircuitBreake(100)
    public String myAspectCall3() throws Exception{
        return doCall();
    }

    public String doCall()throws Exception{
        int sleepTime = random.nextInt(200);
        System.out.println("call spends "+sleepTime);
        Thread.sleep(sleepTime);
        return "hello world";
    }


    @RequestMapping("/cb/my/call")
    public String myCall() throws Exception{

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(()->{
            int sleepTime = random.nextInt(200);
            System.out.println("call spends "+sleepTime);
            Thread.sleep(sleepTime);
            return "hello world";
        });

        String result = "";
        try {
            result = future.get(100L,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("熔断！！！");
            future.cancel(true);
            throw new CircuitBreakException("熔断",e);
            //return circuitBreakerCall();
        }
        return result;
    }


    /**
     * 使用hystrix提供的熔断机制
     * 短路演示，100ms如果能执行完就不短路，否则就会短路
     * @return
     */
    @RequestMapping("/cb/call")
    @HystrixCommand(commandKey = "call",
            fallbackMethod = "circuitBreakerCall",
            commandProperties={
                    @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,
                            value="100")
            }
    )
    public String call() throws  Exception{
        int sleepTime = random.nextInt(200);
        System.out.println("call spends "+sleepTime);
        Thread.sleep(sleepTime);
        return "hello world";
    }

    public String circuitBreakerCall(){
        return "fault!!!";
    }
}
