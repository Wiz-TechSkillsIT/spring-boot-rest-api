package com.techskillsit.springrestapi.service;

 import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.dto.TeamProjectStatDto;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public Team postTeam(Team team) {
		return teamRepository.save(team);
	}

	public Team getById(int teamId) throws ResourceNotFoundException {
		Optional<Team> optional = teamRepository.findById(teamId);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("given team ID invalid");
		
		return optional.get();
	}

	public void deleteTeam(Team team) {
		 teamRepository.delete(team);	
	}

	public List<TeamProjectStatDto> teamProjectStat() {
		 
		return teamRepository.teamProjectStat();
	}
 
}
