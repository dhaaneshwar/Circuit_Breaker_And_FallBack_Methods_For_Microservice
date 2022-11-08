package com.example.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.department.VO.Department;
import com.example.department.VO.ResponseTemplateVO;
import com.example.department.entity.User;
import com.example.department.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("Inside saveUser of userService");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Inside getUserWithDepartment of userService");
		ResponseTemplateVO vo=new ResponseTemplateVO();
		User user=userRepository.findByUserId(userId);
		//to call the department microservice we need restTemplate... define the restTemplate in main class
		//restCall to other microservice
//		Department department=restTemplate.getForObject("http://localhost:9001/departments/"+user.getDepartmentId(), Department.class);    
		//after connecting to the eureka server
		Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
		
		vo.setUser(user);
		vo.setDeparment(department);
		return vo;
		
	}

	public Department addDepartment(Department department) {
		
		return restTemplate.postForObject("http://DEPARTMENT-SERVICE/departments/",department,Department.class);
	}
	
}
