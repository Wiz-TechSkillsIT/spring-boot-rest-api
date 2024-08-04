package com.techskillsit.springrestapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_employee")
public class TeamEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@ManyToOne
	private Team team;
	
	@ManyToOne
	private Employee employee;
	
	private LocalDate dateOfTeamJoining;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDateOfTeamJoining() {
		return dateOfTeamJoining;
	}

	public void setDateOfTeamJoining(LocalDate dateOfTeamJoining) {
		this.dateOfTeamJoining = dateOfTeamJoining;
	} 
	
	
}
