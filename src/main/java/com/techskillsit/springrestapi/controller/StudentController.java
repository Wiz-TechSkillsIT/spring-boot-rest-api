package com.techskillsit.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techskillsit.springrestapi.exceptions.InvalidInputException;
import com.techskillsit.springrestapi.exceptions.ResourceNotFoundException;
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
	
	@GetMapping("/api/student/getall")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/api/student/getone/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable int id) {
		Student student;
		try {
			student = studentService.getOneStudent(id);
			return ResponseEntity.ok().body(student); 
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		 
	}

	@PutMapping("/api/student/update/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student) {
		/* Go to Service and check if ID is valid and fetch existing record  */
		Student studentDB;
		try {
			studentDB = studentService.getOneStudent(id);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		/*Replace studentDB fields with new Input values */
		studentDB.setCity(student.getCity());
		studentDB.setContact(student.getContact());
		
		/* save studentDb so that DB gets  updated values*/
		return ResponseEntity.ok().body(studentService.postStudent(studentDB)); 
	}
	
	@DeleteMapping("/api/student/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		/* Go to Service and validate the ID  */
 		try {
			 studentService.getOneStudent(id);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		studentService.deleteStudent(id);
			return ResponseEntity.ok().body("Record deleted");
	}
}
/* status code : 200 OK 
 * 400: bad request 
 * 
 */