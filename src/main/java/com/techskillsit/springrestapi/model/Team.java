package com.techskillsit.springrestapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private String title; 
	
	private LocalDate dateOfFormation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDateOfFormation() {
		return dateOfFormation;
	}

	public void setDateOfFormation(LocalDate dateOfFormation) {
		this.dateOfFormation = dateOfFormation;
	} 
	
	
}
