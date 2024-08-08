package com.techskillsit.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.enums.UserRole;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Employee;
import com.techskillsit.springrestapi.model.UserInfo;
import com.techskillsit.springrestapi.service.EmployeeService;
import com.techskillsit.springrestapi.service.UserInfoService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/api/employee/post")
	public Employee postEmployee(@RequestBody Employee employee) {
		return employeeService.postEmployee(employee);
	}
	
	@PostMapping("/api/employee/signup/{eid}")
	public ResponseEntity<?> signUp(@PathVariable int eid, @RequestBody UserInfo userInfo) {
		try {
			Employee employee = employeeService.getById(eid);
			userInfo.setRole(UserRole.EMPLOYEE);
			
			//encrypt password 
			String plainPassword = userInfo.getPassword();
			String encryptedPassword = passwordEncoder.encode(plainPassword);
			userInfo.setPassword(encryptedPassword);
			//save this userinfo in DB 
			userInfo = userInfoService.postUser(userInfo);
			employee.setUserInfo(userInfo);
			employee = employeeService.postEmployee(employee);
			return ResponseEntity.ok(employee);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body("SignUp denied");
		}
	}
	
	/*
	 * API Path: localhost:8081/api/employee/getall?page=0&size=5

	 * */
	@GetMapping("/api/employee/getall")
	public List<Employee> getAllEmployeesWithPagination(
			@RequestParam(required = false) Integer page , 
			@RequestParam(required = false) Integer size) {
		
		if(page == null || size == null)  
			return employeeService.getAllEmployee(page,size,false);
	 	 
		else  
			return employeeService.getAllEmployee(page,size,true);
		 
			
	}
	
	@GetMapping("/api/employee/name/search")
	public ResponseEntity<?> searchRecordInNameByKeyword(@RequestParam(required = false) String keyword) {
		if(keyword == null) {
			return ResponseEntity.badRequest().body("Keyword missing..");
		}
		List<Employee> list =  employeeService.searchRecordInNameByKeywordJpql(keyword);
		return ResponseEntity.ok(list);
	}
}
