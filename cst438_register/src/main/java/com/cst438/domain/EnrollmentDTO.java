package com.cst438.domain;

public class EnrollmentDTO {
	public int id;
	public String studentEmail;
	public String studentName;
	public int course_id;
	public String course_grade;
	
	public EnrollmentDTO() {
		this.id = 0;
		this.studentEmail=null;
		this.studentName=null;
		this.course_id = 0;
		this.course_grade=null;
	}
	
	
	public EnrollmentDTO(String studentEmail, String studentName, int course_id) {
		this.id = 0;
		this.studentEmail=studentEmail;
		this.studentName=studentName;
		this.course_id = course_id;
	}

	public void setGrade(String grade) {
		this.course_grade = grade;
	}

	@Override
	public String toString() {
		return "EnrollmentDTO [id=" + id + ", studentEmail=" + studentEmail + ", studentName=" + studentName
				+ ", course_id=" + course_id + "]";
	}
	
	
}