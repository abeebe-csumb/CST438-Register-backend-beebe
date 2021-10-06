package com.cst438.domain;

public class StudentDTO {

	public int student_id;
	public String name;
	public String email;
	public String status;
	public int status_code;

	@Override
	public String toString() {
		return "StudentDTO [student_id=" + student_id + ", studentName=" + name + ", studentEmail="
				+ email + ", status=" + status + ", status_code=" + status_code + "]";
	}
	
}
