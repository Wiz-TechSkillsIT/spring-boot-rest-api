package com.techskillsit.springrestapi.dto;

public class TeamProjectStatDto {
	private String teamName;
	private Long numberOfProjects;

	public TeamProjectStatDto(String teamName, Long numberOfProjects) {
		super();
		this.teamName = teamName;
		this.numberOfProjects = numberOfProjects;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getNumberOfProjects() {
		return numberOfProjects;
	}

	public void setNumberOfProjects(Long numberOfProjects) {
		this.numberOfProjects = numberOfProjects;
	}

}
