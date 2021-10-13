package com.cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String email;
	
	@Override
	public String toString() {
		return "RoleDTO [email=" + email + "]";
	}
}
