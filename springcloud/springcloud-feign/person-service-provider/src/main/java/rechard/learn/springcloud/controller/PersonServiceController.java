package rechard.learn.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.springcloud.bean.Person;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class PersonServiceController {

    Logger logger = LoggerFactory.getLogger(PersonServiceController.class);
    private volatile Map<Integer ,Person> repository = new ConcurrentHashMap<>();

    private final static Random random = new Random();

    @PostMapping(value = "/mars/call")
    public String marsCall() {
        return "0";
    }

    @PostMapping(value = "/person/save")
    public boolean personSave(@RequestBody Person person) {
        repository.put(person.getId(), person);
        return true;
    }
    @PostMapping("/person/list")
    @HystrixCommand(commandKey = "personList",
            fallbackMethod = "personListFault",
            commandProperties={
                    @HystrixProperty(name= HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,
                            value="200")
            }
    )
    public Collection<Person> personList() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(300);
        Thread.sleep(randomInt);
        return repository.values();
    }

    private  Collection<Person>  personListFault(){
        logger.warn("熔断，返回空");
        return Collections.emptyList();
    }

}
