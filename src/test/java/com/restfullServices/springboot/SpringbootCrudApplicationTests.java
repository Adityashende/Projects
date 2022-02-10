package com.restfullServices.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.restfullServices.springboot.controller.UserController;
import com.restfullServices.springboot.entity.User;
import com.restfullServices.springboot.repository.UserRepository;

import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootCrudApplicationTests {

	@Autowired
	private UserController userController;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void getAllUserTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User("Aditya","Shende","adityashe@cybage.com"),new User("Ram","Sham","ramsham@cybage.com")).collect(Collectors.toList()));
		assertEquals(2, userController.getAllUser().size());
	}
	
	@Test
	public void saveUserTest() {
		User user=new User("Name","LName","Email");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userController.createUser(user));
		
	}
	
	@Test
	public void deleteUserTest() {
		User user=new User("Name","LName","Email");
		userController.deleteUser(1);
		verify(userRepository,times(1)).delete(user);
		
	}
	
//	@Test
//	public void testGetUserById() {
//	User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
//	System.out.println(user.getFirstName());
//	Assert.assertNotNull(user);
//	}
//	
//	@Test
//	public void deleteUserTest() {
//		 long id = 1;
//		when(userRepository.findById(id))
//				.thenReturn(Stream.of(new User("Danile", "James","james@gmail.com")).collect(Collectors.toList()));
//		assertEquals(1, userController.getUserById(id));
//	}
	@Test
	void contextLoads() {
	}

}
