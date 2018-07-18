package rechard.learn.qualifier;

import org.springframework.stereotype.Component;

@Component("uService")
public class UserServiceImpl implements UserService{

	public String findUerNameById(String id) {
		return "id:" +id +",name:uService";
	}

}
