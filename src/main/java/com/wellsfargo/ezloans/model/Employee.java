package com.wellsfargo.ezloans.model;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

enum Gender {
	Male,
	Female,
	Other
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
	
	@Id
	@UuidGenerator
	private String employee_id;
	
	@Column(nullable=false)
	private String employee_name;
	
	@Column(nullable=false)
	private String designation;
	
	@Column(nullable=false)
	private String department;
	
	@Column(nullable=false)
	private Gender gender;
	
	@Column(nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	private Date doj;
}
