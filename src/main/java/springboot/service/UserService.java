package springboot.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import springboot.dao.UserDao;
import springboot.entity.User;


@Service
public class UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("userRestData")
	private UserDao userDao;
	
	public Collection<User> getAllUsers(){
		return userDao.getAllUsers();
	}

	public User getUser(String username) {
		
		List<User> userList = userDao.getUserList();
		
        for (User user : userList) {
			if(user.getUsername().equals(username)){
				return user;
			}
		}
        return null;
	}	
}
