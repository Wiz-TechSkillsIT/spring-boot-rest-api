package com.techskillsit.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techskillsit.springrestapi.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	UserInfo findByUsername(String username);

}
