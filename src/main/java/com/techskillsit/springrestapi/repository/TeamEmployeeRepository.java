package com.techskillsit.springrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techskillsit.springrestapi.model.Employee;
import com.techskillsit.springrestapi.model.Team;
import com.techskillsit.springrestapi.model.TeamEmployee;

public interface TeamEmployeeRepository extends JpaRepository<TeamEmployee, Integer> {

	@Query(nativeQuery = true, value="select e.id,e.name,e.date_of_joining,e.job_title "
			+ " from team t , team_employee te , employee e "
			+ " where t.id  = te.team_id AND te.employee_id = e.id "
			+ " AND t.id=?1")
	List<Object[]> getEmployeeByTeam(int teamId);
	
	@Query("select te.employee from TeamEmployee te where te.team.id=?1")
	List<Employee> getEmployeeByTeamJpql(int teamId);

	@Query("select te.team from TeamEmployee te where te.employee.id=?1")
	List<Team> getTeamsByEmployee(int employeeId);

	List<TeamEmployee> findByEmployeeId(int employeeId);

}
/*
 * 1. Native Query : Query the DB 
 * 2. JPQL  : Query the class 
 * */

/*
 * findAll , save, delete(),deleteById() , findById
 * List<TeamEmployee> findByTeamId(teamId)
 * */
