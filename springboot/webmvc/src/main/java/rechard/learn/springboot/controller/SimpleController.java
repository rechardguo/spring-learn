package rechard.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.springboot.MQListenerJMSHandlerDispatcher;
import rechard.learn.springboot.entity.User;

@RestController
public class SimpleController {

    @Autowired
    MQListenerJMSHandlerDispatcher dispatch;

    @RequestMapping("/process")
    public String process(){
        User user = new User();
        user.setRequestType(1);
        dispatch.process(user);
        return "ok";
    }
}
