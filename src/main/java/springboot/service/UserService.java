package springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import springboot.dao.UserDao;
import springboot.entity.User;

@Service
public class UserService {
	
	@Autowired
	@Qualifier("userRestData")
	private UserDao userDao;
	
	public Collection<User> getAllUsers(){
		return userDao.getAllUsers();
	}	
}
