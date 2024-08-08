package com.techskillsit.springrestapi.repository;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techskillsit.springrestapi.dto.TeamProjectStatDto;
import com.techskillsit.springrestapi.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{

	@Query("select new com.techskillsit.springrestapi.dto."
			+ " TeamProjectStatDto(p.team.title,count(p.id)) "
			+ " from Project p "
			+ " group by p.team.title")
	List<TeamProjectStatDto> teamProjectStat();
 
}
