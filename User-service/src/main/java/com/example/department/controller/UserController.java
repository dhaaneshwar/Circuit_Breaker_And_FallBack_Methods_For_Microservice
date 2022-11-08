package com.example.department.controller;

import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.VO.Department;
import com.example.department.VO.ResponseTemplateVO;
import com.example.department.entity.User;
import com.example.department.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		log.info("Inside saveUser of userController");
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
		log.info("Inside saveUser of userController");
		return userService.getUserWithDepartment(userId);
	}
	
	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department department) {
		return userService.addDepartment(department);
	}

	private static final String BASEURL="http://DEPARTMENT-SERVICE/departments";
	private static final String USER_SERVICE="userService";
	@GetMapping("/getDepartment/{department}")
	@CircuitBreaker(name=USER_SERVICE,fallbackMethod = "getAllAvailableDepartments")
	public List<Object> FindByDepartment(@PathVariable("department") String department){

		String url=department==null?BASEURL:BASEURL+"/getDepartment/"+department;
		Object[] departments= restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/getDepartment/EEE", Object[].class);
		return Arrays.asList(departments);
	}

	public List<Object> getAllAvailableDepartments(Exception e){
		return Stream.of(new Department(1l,"AAA","SECE","7228")).collect(Collectors.toList());
	}

}
