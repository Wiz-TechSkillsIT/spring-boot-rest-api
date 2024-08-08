package com.techskillsit.springrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
import com.techskillsit.springrestapi.model.Employee;
import com.techskillsit.springrestapi.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee postEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	public Employee getById(int employeeId) throws ResourceNotFoundException {
		Optional<Employee> optional = employeeRepository.findById(employeeId);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Employee Id given is Invalid");
		return optional.get();
	}

	public void deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
		 
	}

	public List<Employee> getAllEmployee(Integer page, Integer size, boolean isPaginationActive) {
		if (isPaginationActive == true) {
			// paginated output
			PageRequest pageRequest 
				= PageRequest.of(page, size,Sort.by(Direction.ASC, "dateOfJoining"));
			
			return employeeRepository.findAll(pageRequest).getContent();

		}

		// no pagination
		return employeeRepository.findAll(Sort.by(Direction.ASC, "dateOfJoining"));
	}

	public List<Employee> searchRecordInNameByKeyword(String keyword) {
		
		return employeeRepository.findByNameContaining(keyword);
	}
	
public List<Employee> searchRecordInNameByKeywordJpql(String keyword) {
		
		return employeeRepository.searchKeywordInName(keyword);
	}
	
}
