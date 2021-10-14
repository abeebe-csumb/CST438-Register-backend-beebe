package com.cst438.service;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;


public class GradebookServiceMQ extends GradebookService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	Queue gradebookQueue;
	
	public GradebookServiceMQ() {
		System.out.println("MQ grade book service");
	}
	
	// send message to grade book service about new student enrollment in course
	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {

        Course course  = courseRepository.findByCourse_id(course_id);
        Student student = studentRepository.findByEmail(student_email);
        
        Enrollment e = new Enrollment();
		e.setStudent(student);
		e.setCourse(course);
		
        this.gradebookQueue = new Queue("gradebook-queue");
        this.rabbitTemplate.convertAndSend(gradebookQueue.getName(), e);
        System.out.println(" [x] Sent '" + e + "'");
	}
	
	@RabbitListener(queues = "registration-queue")
	@Transactional
	public void receive(CourseDTOG courseDTOG) {
		
		//TODO  complete this method in homework 4
		
	}
}
