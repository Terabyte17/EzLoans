package com.wellsfargo.ezloans.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
	private String employeeId;
	
	@Column(nullable=false)
	private String employeeName;
	
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
	
	@OneToMany(mappedBy="emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ItemPurchase> itemsPurchased;
	
}
