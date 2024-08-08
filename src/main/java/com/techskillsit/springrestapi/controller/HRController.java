package com.techskillsit.springrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.enums.UserRole;
import com.techskillsit.springrestapi.model.HR;
import com.techskillsit.springrestapi.model.UserInfo;
import com.techskillsit.springrestapi.service.HRService;
import com.techskillsit.springrestapi.service.UserInfoService;

@RestController
public class HRController {

	@Autowired
	private HRService hrService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/api/hr/signup")
	public HR signUpHr(@RequestBody HR hr) {
		UserInfo userInfo = hr.getUser();
		userInfo.setRole(UserRole.HR);
		//encrypt the password 
		String painText = hr.getUser().getPassword();
		String encryptedPassword = passwordEncoder.encode(painText);
		userInfo.setPassword(encryptedPassword);
		
		//save userinfo in DB
		userInfo = userInfoService.postUser(userInfo);
		
		hr.setUser(userInfo);
		return hrService.postHr(hr);
	}
	
	
}
