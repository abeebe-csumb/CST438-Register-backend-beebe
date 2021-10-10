package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin(origins = "https://cst438-registrationfe-abeebe.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:3000/")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/student")
	public Student getStudent() {
		
		String email = "test@csumb.edu";   // student's email 
		
		Student student = studentRepository.findByEmail(email);
		if (student != null) {
			return student;
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student not found. " );
		}
	}
	
	// add a new student to the system
	@PostMapping("/student")
	@Transactional
	public Student addStudent( @RequestBody StudentDTO studentDTO ) { 
		
		Student student = studentRepository.findByEmail(studentDTO.email);
		
		// check if student exists using email
		
		if (student == null) { // add a new student to the database
			student = new Student();
			student.setEmail(studentDTO.email);
			student.setName(studentDTO.name);
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "A student with that email already exists.");
		}
		
	}
	
	// place or release a hold on a students registration
	@PostMapping("/student/updateRegistration")
	@Transactional
	public String updateRegistrationHold( @RequestParam("email") String email, @RequestParam("status") int status ) {
		Student student = studentRepository.findByEmail(email);

		if (student != null) {
			student.setStatusCode(status);
			studentRepository.save(student);
			
			return "Registration status updated";
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student not found. " );
		}
	}
}
