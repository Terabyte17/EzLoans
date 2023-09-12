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
	
	private @NonNull String employee_name;
	
	private @NonNull String designation;
	
	private @NonNull String department;
	
	private @NonNull Gender gender;
	
	private @NonNull Date dob;
	
	private @NonNull Date doj;
}
