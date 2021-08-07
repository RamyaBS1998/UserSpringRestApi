package com.app.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user)  {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id)  {
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user)  {
		return userRepository.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<User> getAllUser()  {
		
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Boolean isUserRecordExists(int id) {

		return userRepository.existsById(id);
	}

	@Override
	public Optional<User> findUserById(int id) {
		
		return userRepository.findById(id);
	}

}
