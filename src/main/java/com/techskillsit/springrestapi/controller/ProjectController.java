package com.techskillsit.springrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.dto.ProjectResponseDto;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Project;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.service.ProjectService;
import com.techskillsit.springrestapi.service.TeamService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService; //DI
	
	@Autowired
	private TeamService teamService;
	/*
	 * AIM: Fetch all products on the basis of given teamId 
	 * Path: /api/project/{teamId}
	 * pathVariable: teamId
	 * Method: GET
	 * */
	
	@GetMapping("/api/project/{teamId}")
	public ResponseEntity<?> getProjectsByTeam(@PathVariable int teamId) {
		try {
			List<Project> projects = projectService.getProjectsByTeam(teamId);
			return ResponseEntity.ok(projects);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PostMapping("/api/project/post/{teamId}")
	public ResponseEntity<?> postProject(@PathVariable int teamId, @RequestBody Project project) {
		/* go to db thru service and fetch team by given team id */
		Team team;
		try {
			team = teamService.getById(teamId);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		/* attach team to project */
		project.setTeam(team);
		/* save the project in DB  */
		project = projectService.postProject(project);
		
		return ResponseEntity.ok(project); 
	}
	
	@PutMapping("/api/project/team/update/{teamId}/{projectId}")
	public ResponseEntity<?> updateTeam(@PathVariable int teamId, @PathVariable int projectId) {
		/* go to db thru service and fetch team by given team id */
		Team team;
		Project project;
		try {
			team = teamService.getById(teamId);
			project = projectService.getById(projectId);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		project.setTeam(team);
		
		project = projectService.postProject(project);
		return ResponseEntity.ok(project); 
	}
	
	@GetMapping("/api/project/getall")
	public List<ProjectResponseDto> getAll() {
		List<Project> list =  projectService.getAll();
		List<ProjectResponseDto> listDto = new ArrayList<>();
		
		list.stream().forEach(p->{
			ProjectResponseDto dto = new ProjectResponseDto();
			dto.setId(p.getId());
			dto.setProposedEndDate(p.getProposedEndDate());
			dto.setStartDate(p.getStartDate());
			dto.setTitle(p.getTitle());
			dto.setTeamTitle(p.getTeam().getTitle());
			listDto.add(dto);
		});
		
		return listDto;
				
	}
}








