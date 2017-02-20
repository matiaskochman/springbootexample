package springboot.dao;

import java.util.Collection;
import java.util.List;

import springboot.entity.User;

public interface UserDao {
	Collection<User> getAllUsers();

	User getUser(String username);
	
	List<User> getUserList();
}
