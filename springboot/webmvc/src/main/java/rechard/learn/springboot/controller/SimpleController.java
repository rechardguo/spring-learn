package rechard.learn.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.springboot.MQListenerJMSHandlerDispatcher;
import rechard.learn.springboot.entity.User;
@Api("简单的请求类")
@RestController
public class SimpleController {
    @Autowired
    MQListenerJMSHandlerDispatcher dispatch;

    @ApiOperation(value="处理请求", notes="模拟mq接收消息")
    @ApiImplicitParam(name = "type", value = "请求消息类型", required = true, dataType = "Integer", paramType = "path")
    @PostMapping("/process/{type}")
    public String process(@PathVariable("type") int type){
        User user = new User();
        user.setRequestType(1);
        dispatch.process(user);
        return "ok";
    }
}
