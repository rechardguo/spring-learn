package rechard.learn.springcloud.controller;

import org.springframework.web.bind.annotation.*;
import rechard.learn.springcloud.bean.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserController {

    private Map<Integer,User> userRepository = new ConcurrentHashMap<>();

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        userRepository.put(user.getId(),user);
    }

    @RequestMapping("/user/list")
    public Map getAllUsers(){
        return userRepository;
    }

}
