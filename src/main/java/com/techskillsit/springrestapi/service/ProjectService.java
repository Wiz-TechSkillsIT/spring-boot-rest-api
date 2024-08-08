package com.techskillsit.springrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Project;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.repository.ProjectRepository;
import com.techskillsit.springrestapi.repository.TeamRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TeamRepository teamRepository;
	
	public List<Project> getProjectsByTeam(int teamId) throws ResourceNotFoundException {
		Optional<Team> optional = teamRepository.findById(teamId);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Team Id given is Invalid");
		
		Team team = optional.get();
		/*Instance variable methods of JPARepository */
		List<Project> projects =projectRepository.findByTeamId(team.getId());
		return projects;
	}

	public Project postProject(Project project) {
		return projectRepository.save(project);
	}

	public Project getById(int projectId) throws ResourceNotFoundException {
		Optional<Project> optional =  projectRepository.findById(projectId);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Project Id given is Invalid");
		
		return optional.get();
	}

	public void updateAllProjects(List<Project> list) {
		projectRepository.saveAll(list);
		
	}

	public List<Project> getAll() {
		 
		return projectRepository.findAll();
	}
}
