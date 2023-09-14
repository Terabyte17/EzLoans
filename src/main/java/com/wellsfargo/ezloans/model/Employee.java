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
	
	//@Column(name="department", nullable=false)
	private String department;
	
	@Column(nullable=false)
	private Gender gender;
	
	@Column(nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	private Date doj;
	
	@OneToMany(mappedBy="emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ItemPurchase> itemsPurchased;
	
	@OneToMany(mappedBy="emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EmployeeLoan> loanCards;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Set<ItemPurchase> getItemsPurchased() {
		return itemsPurchased;
	}

	public void setItemsPurchased(Set<ItemPurchase> itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}

	public Set<EmployeeLoan> getLoanCards() {
		return loanCards;
	}

	public void setLoanCards(Set<EmployeeLoan> loanCards) {
		this.loanCards = loanCards;
	}
	
	
	
}
