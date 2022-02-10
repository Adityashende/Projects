package com.restfullServices.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfullServices.springboot.entity.User;
import com.restfullServices.springboot.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public List<User> getAllUser(){
		return this.userServices.getAllUser();
	
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		
		return this.userServices.getUserById(userId);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userServices.createUser(user);
		
	}
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable ("id") long userId) {
		
		return this.userServices.updateUser(user, userId);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity < User > deleteUser(@PathVariable("id") long userId) {
		
		return this.userServices.deleteUser(userId);
	}
	

}











