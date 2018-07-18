package rechard.learn.qualifier;

import org.springframework.stereotype.Component;

@Component("uService2")
public class UserServiceImpl2 implements UserService{

	public String findUerNameById(String id) {
		return "id:" +id +",name:uService2";
	}

}
