package rechard.learn.springcloud.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import rechard.learn.springcloud.bean.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PersonServiceFallback implements PersonService {

    @Override
    public boolean personSave(Person person) {
        return false;
    }

    @Override
    public List<Person> personList() {
        return Collections.emptyList();
    }
}
