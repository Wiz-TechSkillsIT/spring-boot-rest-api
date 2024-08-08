package com.techskillsit.springrestapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.enums.EmployeeJobTitle;
import com.techskillsit.springrestapi.model.Employee;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.model.TeamEmployee;
import com.techskillsit.springrestapi.repository.TeamEmployeeRepository;

@Service
public class TeamEmployeeService {

	@Autowired
	private TeamEmployeeRepository teamEmployeeRepository;

	public TeamEmployee postTeamEmployee(TeamEmployee teamEmployee) {
		 
		return teamEmployeeRepository.save(teamEmployee);
	}

	public List<Employee> getEmployeeByTeam(int teamId) {
		 
		List<Object[]> list =  teamEmployeeRepository.getEmployeeByTeam(teamId);
		List<Employee> employees = new ArrayList<>();
		for(Object[] arry : list) {
			Employee employee = new Employee();
			employee.setId((int)arry[0]);
			employee.setName((String)arry[1]);
			employee.setDateOfJoining((LocalDate.parse((String)arry[2].toString())));
			employee.setJobTitle( EmployeeJobTitle.valueOf((String)arry[3]));
			employees.add(employee);
		}
		return employees;
	}
	
	public List<Employee> getEmployeeByTeamJpql(int teamId) {
		 
		 return teamEmployeeRepository.getEmployeeByTeamJpql(teamId); 
	}

	public List<Team> getTeamsByEmployee(int employeeId) {
		 
		return teamEmployeeRepository.getTeamsByEmployee(employeeId);
	}

	public List<TeamEmployee> getTeamEmployeeByEmployee(int employeeId) {
		 
		return teamEmployeeRepository.findByEmployeeId(employeeId);
	}

	public void postbatchTeamEmployee(List<TeamEmployee> list) {
		 teamEmployeeRepository.saveAll(list);
		
	}
	
}
