package com.techskillsit.springrestapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Project { //table = project

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private String title; 
	
	private LocalDate startDate; 
	
	private LocalDate proposedEndDate;
	
	@ManyToOne
	private Team team;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getProposedEndDate() {
		return proposedEndDate;
	}

	public void setProposedEndDate(LocalDate proposedEndDate) {
		this.proposedEndDate = proposedEndDate;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}  
	
	
}
