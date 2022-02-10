package com.restfullServices.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.restfullServices.springboot.entity.User;
import com.restfullServices.springboot.exception.UserNotFound;
import com.restfullServices.springboot.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	public UserRepository userRepository;
	
	
	public List<User> getAllUser(){
		List<User> users= this.userRepository.findAll();
		System.out.println("All fetched users : "+users);
		return users;
	}
	
	// get user by id
	
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() ->new UserNotFound("User Not found with id : "+userId));
	}
	
	
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
		
	}
	
	 // update user
	
	public User updateUser(@RequestBody User user,@PathVariable ("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFound("User not found with id :" + userId));
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setEmail(user.getEmail());
	        return this.userRepository.save(existingUser);
	    }
	
	// delete user by id
	
    public ResponseEntity < User > deleteUser(@PathVariable("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFound("User not found with id :" + userId));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
	
	


