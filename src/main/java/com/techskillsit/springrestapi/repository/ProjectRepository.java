package com.techskillsit.springrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techskillsit.springrestapi.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	List<Project> findByTeamId(int id);

}
