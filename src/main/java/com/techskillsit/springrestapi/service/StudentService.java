package com.techskillsit.springrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.exceptions.InvalidInputException;
import com.techskillsit.springrestapi.model.Student;
import com.techskillsit.springrestapi.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student postStudent(Student student) {
		/* call save method of repo and save this object in DB  */
		return studentRepository.save(student);
		 
	}

	public void validateStudent(Student student) throws InvalidInputException {
		 if(student == null) {
			 throw new InvalidInputException("Invalid Data provided");
		 }
		 if(student.getName().equals("") || student.getName() == null) {
			 throw new InvalidInputException("Invalid name value provided");
		 }
		 if(student.getCity().equals("") || student.getCity() == null) {
			 throw new InvalidInputException("Invalid city value provided");
		 }
		
	}

}