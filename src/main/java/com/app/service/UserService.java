package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.User;



public interface UserService {

	 User createUser(User user) ;
	
	void deleteUser(int id) ;
	
	 User updateUser(User user);
	
	 List<User> getAllUser();
	
	 Boolean isUserRecordExists(int id);
	
	 Optional<User> findUserById(int id);
	
}
