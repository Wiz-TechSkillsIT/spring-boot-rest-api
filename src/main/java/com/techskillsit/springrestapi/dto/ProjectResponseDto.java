package com.techskillsit.springrestapi.dto;

import java.time.LocalDate;

public class ProjectResponseDto {
	private int id; 
	
	private String title; 
	
	private LocalDate startDate; 
	
	private LocalDate proposedEndDate;
	
	private String teamTitle;

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

	public String getTeamTitle() {
		return teamTitle;
	}

	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	
	
}
