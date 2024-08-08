package com.techskillsit.springrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techskillsit.springrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByNameContaining(String keyword);

	@Query("select e from Employee e where e.name LIKE %?1%")
	List<Employee> searchKeywordInName(String keyword);

}
