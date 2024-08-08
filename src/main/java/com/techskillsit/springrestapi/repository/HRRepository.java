package com.techskillsit.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techskillsit.springrestapi.model.HR;

public interface HRRepository extends JpaRepository<HR, Integer>{

}
