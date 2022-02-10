package com.restfullServices.springboot.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.restfullServices.springboot.entity.User;

public class RestClient {
	
private static final String GET_ALL_USERS_API="http://localhost:8080/api/users";
private static final String GET_USER_BY_ID="http://localhost:8080/api/users/{id}";

//private static final String CREATE_USER_API="http://localhost:8080/api/users";	
//private static final String UPDATE_USER_API="http://localhost:8080/api/users/{id}";	
//private static final String DELETE_USER_API="http://localhost:8080/api/users/{id}";	


static RestTemplate restTemplate=new RestTemplate();
public static void main(String[] args) {
	callGetAllUserAPI();
	callGetUserByIdAPI();
}

private static void callGetAllUserAPI() {
	
	org.springframework.http.HttpHeaders headers=new org.springframework.http.HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	
	HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
	
	ResponseEntity<String> result=restTemplate.exchange(GET_ALL_USERS_API,HttpMethod.GET,entity,String.class);
	System.out.println(result);
}

private static void callGetUserByIdAPI() {
	Map<String,Integer> param=new HashMap<>();
	param.put("id", 2);
	
	User user=restTemplate.getForObject(GET_USER_BY_ID, User.class,param);
	System.out.println(user.getFirstName());
	System.out.println(user.getLastName());
	System.out.println(user.getEmail());
}
}
