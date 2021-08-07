package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;

import com.app.service.UserService;

@RestController
@RequestMapping(value = "/Student")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	public ResponseEntity<User> addStudent(@RequestBody User requestuser) {

		User user = userService.createUser(requestuser);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity("The User record is not found!!", HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	public ResponseEntity<User> upadateUser(@RequestBody User requestuser) {
		Boolean isUserExists = userService.isUserRecordExists(requestuser.getId());

		if (isUserExists) {
			User user = userService.updateUser(requestuser);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity("The User record is not found!!", HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteStudent(@PathVariable int id) {
		
		Boolean isUserExists = userService.isUserRecordExists(id);

		if (isUserExists) {
			userService.deleteUser(id);
			return new ResponseEntity("User record " + id + " Deleted Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity("User record " + id + " Not Found!", HttpStatus.NOT_FOUND);
		}

		
	}

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
	public List<User> getUser() {
		List<User> userList = userService.getAllUser();
		return userList;
	}

	@RequestMapping(value = "/getStudentByid/{id}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		Optional<User> user = userService.findUserById(id);
		User userObj = user.get();
		if (userObj != null) {
			return new ResponseEntity<User>(userObj, HttpStatus.CREATED);
		} else {
			return new ResponseEntity("The User record is not found!!", HttpStatus.NOT_FOUND);

		}

	}
	
	// Added a bug to test the FindBugs Plugin
	public String execute() {
        String name = null;
        System.out.println("This is the output from excecte : " + name);
        return name.toUpperCase();
        
    }
}
