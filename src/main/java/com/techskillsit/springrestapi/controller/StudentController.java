package com.techskillsit.springrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.exceptions.InvalidInputException;
import com.techskillsit.springrestapi.model.Student;
import com.techskillsit.springrestapi.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService; //100X 
	
	@PostMapping("/api/student/post")
	public ResponseEntity<?> postStudent(@RequestBody Student student) {
		/* go to DB through service and insert this student object*/
		try {
			studentService.validateStudent(student);
		} catch (InvalidInputException e) {
			 return ResponseEntity.badRequest().body(e.getMessage()); //String
		}
		student =  studentService.postStudent(student);
		 return ResponseEntity.ok().body(student);
	}
}
/* status code : 200 OK 
 * 400: bad request 
 * 
 */