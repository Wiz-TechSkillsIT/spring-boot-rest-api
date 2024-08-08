package com.techskillsit.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.dto.TeamProjectStatDto;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Project;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.service.ProjectService;
import com.techskillsit.springrestapi.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private ProjectService projectService; 
	
	@PostMapping("/api/team/post")
	public Team postTeam(@RequestBody Team team) {
		return teamService.postTeam(team);
	}
	
	@DeleteMapping("/api/team/delete/{id}")
	public ResponseEntity<?> deleteTeam(@PathVariable int id) {
		/*Fetch team from id */
		/*Fetch all projects for this team */
		Team team = null;
		List<Project> list = null;
		
		try {
		    team = teamService.getById(id);
		    list = projectService.getProjectsByTeam(team.getId());
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		 
		/*Disconnect all projects from this team */
		list.stream().forEach(p->{
			p.setTeam(null);
		});
		
		/* Update all projects in DB */
		projectService.updateAllProjects(list);
		
//		for(Project p : list) {
//			p.setTeam(null);
//		}
		
		/*Delete this team */
		 teamService.deleteTeam(team);
		 return ResponseEntity.ok("Team record deleted and project connection detached");
	}
	
	@GetMapping("/api/team/project/stat")
	public List<TeamProjectStatDto> teamProjectStat() {
		List<TeamProjectStatDto> list = teamService.teamProjectStat();
		return list; 
	}
	
	 
}
