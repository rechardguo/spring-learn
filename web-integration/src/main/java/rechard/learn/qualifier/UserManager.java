package rechard.learn.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

	@Autowired
	@Qualifier("uService")
	UserService service;
	
/*	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
*/
	public String getUserNameById(String id){
		return this.service.findUerNameById(id);
	}
}
