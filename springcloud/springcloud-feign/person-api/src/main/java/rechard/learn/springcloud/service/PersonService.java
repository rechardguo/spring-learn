package rechard.learn.springcloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rechard.learn.springcloud.bean.Person;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@FeignClient(value = "${remote.service.name}",fallback = PersonServiceFallback.class)// 服务提供方应用的名称
@Service
public interface PersonService {

    @PostMapping("/person/save")
    public boolean personSave(@RequestBody Person person);

    @PostMapping("/person/list")
    public Collection<Person> personList();
}
