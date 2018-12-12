package rechard.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rechard.learn.springcloud.bean.Person;
import rechard.learn.springcloud.bean.User;
import rechard.learn.springcloud.service.PersonService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController implements PersonService {

    PersonService personService;
    @Autowired
    public PersonController( PersonService personService){
        this.personService=personService;
    }

    @PostMapping("/person/save")
    public boolean personSave(@RequestBody Person person){
        return personService.personSave(person);
    }

    @PostMapping("/person/list")
    public Collection<Person> personList(){
        return (Collection<Person>)personService.personList();
    }

}
