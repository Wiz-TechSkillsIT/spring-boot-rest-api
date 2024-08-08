package com.techskillsit.springrestapi.model;

import java.time.LocalDate;

import com.techskillsit.springrestapi.enums.EmployeeJobTitle;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private String name; 
	
	@Enumerated(EnumType.STRING)
	private EmployeeJobTitle jobTitle;  
	
	private LocalDate dateOfJoining;

	@OneToOne
	private UserInfo userInfo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	
	public EmployeeJobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(EmployeeJobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	} 
	
	
	
}
