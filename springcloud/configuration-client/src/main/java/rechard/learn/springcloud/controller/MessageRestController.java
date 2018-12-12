package rechard.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageRestController {
    @Value("${message}")
    private String message;

    @Autowired
    Environment environment;

    @RequestMapping("/message")
    String getMessage() {
        String msg =environment.getProperty("message");
        return msg;
    }
}
