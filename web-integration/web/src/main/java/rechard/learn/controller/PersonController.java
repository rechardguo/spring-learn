package rechard.learn.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rechard.learn.bean.User;

import javax.validation.Valid;

@RestController
public class PersonController {


    @RequestMapping(value="/user/test")
    public User test(@RequestBody User user){
        System.out.println(user.getName());
        return new User();
    }


    @RequestMapping(value="/user/test2")
    public User testWithValidate(@Valid @RequestBody User user){
        System.out.println(user.getName());
        return new User();
    }


    @PostMapping(value = "/user/json/to/properties",
            produces = "application/properties+person" // 响应类型
    )
    @ResponseBody
    public User personJsonToProperties(  @RequestBody User user) {
        // @RequestBody 的内容是 JSON
        // 响应的内容是 Properties
        return user;
    }

    @PostMapping(value = "/user/properties/to/json",
            consumes = "application/properties+person", // 请求类型 // Content-Type
            produces =  MediaType.APPLICATION_JSON_VALUE  /* 响应类型 // Accept*/
    )
    @ResponseBody
    public User personPropertiesToJson(@RequestBody User user) {
        // @RequestBody 的内容是 Properties
        // 响应的内容是 JSON
        return user;
    }


}
