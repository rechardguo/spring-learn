package rechard.learn.springboot.springstatemachine.demo.groupadd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @RequestMapping("/group/list")
    public List<Group> list(){
        return groupService.listAll();
    }

    @PostMapping("/group/create")
    public boolean  create(@RequestBody Group group){
        groupService.create(group);
        return true;
    }

    @RequestMapping("/group/{id}/{event}")
    public boolean handle(@PathVariable("id")Integer id,@PathVariable("event") String event){
        return groupService.handleAction(id,event);
    }

}
