package com.wellsfargo.ezloans.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
	
	@Column(name="department", nullable=false)
	private String department;
	
	@Column(nullable=false)
	private Gender gender;
	
	@Column(nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	private Date doj;
	
	//need to add unique constraint
	@Column(nullable=false, unique=true)
	private String email;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="admin_id", referencedColumnName="id")
	@JsonIgnoreProperties(value = {"emp"}, allowSetters=true)
	private Admin admin;
	
	@OneToMany(mappedBy="emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ItemPurchase> itemsPurchased;
	
	@OneToMany(mappedBy="emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EmployeeLoan> loanCards;
	
	
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Employee() {
		super();
	}

	public Employee(String employeeId, String employeeName, String designation, String department, Gender gender,
			Date dob, Date doj, String email, Admin admin) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.designation = designation;
		this.department = department;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
		this.email = email;
		this.admin = admin;
	}


	public Employee(String employeeId, String employeeName, String designation, String department,
			Date dob, Date doj, String email, Admin admin, Set<ItemPurchase> itemsPurchased, Set<EmployeeLoan> loanCards) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.designation = designation;
		this.department = department;
		this.dob = dob;
		this.doj = doj;
		this.email = email;
		this.itemsPurchased = itemsPurchased;
		this.loanCards = loanCards;
		this.admin = admin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
