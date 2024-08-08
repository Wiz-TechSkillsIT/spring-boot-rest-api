package com.techskillsit.springrestapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.service.UserInfoService;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/api/login")
	public UserDetails login(Principal principal) {
		//ask spring about loggedIn User 
		String username = principal.getName();
		//fetch userdetials for this username and give it to UI
		return userInfoService.loadUserByUsername(username);
	}
	
}
