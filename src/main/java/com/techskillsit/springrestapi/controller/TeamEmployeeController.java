package com.techskillsit.springrestapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Employee;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.model.TeamEmployee;
import com.techskillsit.springrestapi.service.EmployeeService;
import com.techskillsit.springrestapi.service.TeamEmployeeService;
import com.techskillsit.springrestapi.service.TeamService;

@RestController
public class TeamEmployeeController {

	@Autowired
	private TeamEmployeeService teamEmployeeService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/api/team/employee/{teamId}/{employeeId}")
	public ResponseEntity<?> postTeamEmployee(@PathVariable int teamId, @PathVariable int employeeId) {
		
		/*fetch team from teamId :team */
		/*fetch employee by employeeId : employee */
		Team team = null; 
		Employee employee=null;
		try {
			team = teamService.getById(teamId);
			employee = employeeService.getById(employeeId);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		/* prepare object of TeamEmployee class and attach team and employee objs */
		TeamEmployee teamEmployee = new TeamEmployee();
		teamEmployee.setTeam(team);
		teamEmployee.setEmployee(employee);
		teamEmployee.setDateOfTeamJoining(LocalDate.now());
		
		/* save TeamEmployee*/
		teamEmployee = teamEmployeeService.postTeamEmployee(teamEmployee);
		return ResponseEntity.ok(teamEmployee);
	}
	
	@GetMapping("/api/employee/team/{teamId}")
	public ResponseEntity<?> getEmployeeByTeam(@PathVariable int teamId) {
		/*fetch team from teamId :team */
		Team team = null; 
		try {
			team = teamService.getById(teamId);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		List<Employee> list = teamEmployeeService.getEmployeeByTeamJpql(teamId);
		return ResponseEntity.ok(list); 
	}
	
	@GetMapping("/api/team/employee/{employeeId}")
	public ResponseEntity<?> getTeamsByEmployee(@PathVariable int employeeId) {
		/* Fetch employee by given employeeId  */
		 
		try {
			 employeeService.getById(employeeId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		List<Team> list = teamEmployeeService.getTeamsByEmployee(employeeId);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/api/employee/delete/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) {
		/* Fetch employee by given employeeId  */
		 
		try {
			 employeeService.getById(employeeId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		/* Fetch all TeamEmployee records from relationship table for given employeeId */
		List<TeamEmployee> list = teamEmployeeService.getTeamEmployeeByEmployee(employeeId);
		/* Nullify them */
		list.stream().forEach(e->{
			e.setEmployee(null);
		});
		/* save them again in DB*/
		teamEmployeeService.postbatchTeamEmployee(list);
		/* Delete employee now since it has no trace else where */
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee record deleted with its traces"); 
	}
	
}
