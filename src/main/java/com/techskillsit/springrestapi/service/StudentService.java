package com.techskillsit.springrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techskillsit.springrestapi.exceptions.InvalidInputException;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
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

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getOneStudent(int id) throws ResourceNotFoundException {
		Optional<Student> optional =  studentRepository.findById(id);
		if(optional.isEmpty())
			throw new ResourceNotFoundException("Invlid ID Given");
		return optional.get();
	}
 //Optional<Student> : student : null

	public void deleteStudent(int id) {
		 studentRepository.deleteById(id);
		
	}
}