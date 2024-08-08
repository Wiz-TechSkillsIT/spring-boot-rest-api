package com.techskillsit.springrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.model.HR;
import com.techskillsit.springrestapi.repository.HRRepository;

@Service
public class HRService {

	@Autowired
	private HRRepository hrRepository;

	public HR postHr(HR hr) {
		 
		return hrRepository.save(hr);
	}
}
