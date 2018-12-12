package rechard.learn.springbatch.sample.rmi.server;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private ConcurrentHashMap<Integer,User> map = new ConcurrentHashMap();

    @ServiceActivator(inputChannel = "addUser")
    public void addUser(User user){
        map.put(user.getId(),user);
    }

    @ServiceActivator(inputChannel = "findUserById")
    public User findUserById(int id){
        return map.get(id);
    }
}
