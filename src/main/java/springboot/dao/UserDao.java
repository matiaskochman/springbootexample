package springboot.dao;

import java.util.Collection;

import springboot.entity.User;

public interface UserDao {
	Collection<User> getAllUsers();
}
