package com.cst438.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@RestController
public class CourseController {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	/*
	 * endpoint used by gradebook service to transfer final course grades
	 */
	@PutMapping("/course/{course_id}")
	@Transactional
	public void updateCourseGrades( @RequestBody CourseDTOG courseDTO, @PathVariable("course_id") int course_id) {
		
		List<CourseDTOG.GradeDTO> grades = courseDTO.grades;
		Course course  = courseRepository.findByCourse_id(courseDTO.course_id);
		
		for (CourseDTOG.GradeDTO g : grades) {
			Student student = studentRepository.findByEmail(g.student_email);
			Enrollment e = new Enrollment();
			e.setStudent(student);
			e.setCourse(course);
			e.setCourseGrade(g.grade);
			
			enrollmentRepository.save(e);
		}
	}

}
