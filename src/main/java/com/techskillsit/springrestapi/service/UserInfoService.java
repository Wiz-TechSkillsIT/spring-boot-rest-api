package com.techskillsit.springrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.model.UserInfo;
import com.techskillsit.springrestapi.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public UserInfo postUser(UserInfo userInfo) {
		 
		return userInfoRepository.save(userInfo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetch user details by this given username
		UserInfo user = userInfoRepository.findByUsername(username);
		return user;
	}

}
