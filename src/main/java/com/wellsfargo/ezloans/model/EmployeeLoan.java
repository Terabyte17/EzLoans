package com.wellsfargo.ezloans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EmployeeLoan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanIssueId;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	@JsonIgnoreProperties(value = {"employeeName", "designation", "department", "gender", "dob", "doj", "itemsPurchased", "loanCards"}, allowSetters=true)
	private Employee emp;
	
	@OneToOne
	@JoinColumn(name="loanId")
	@JsonIgnoreProperties(value = {"loanType", "durationInYears", "employeesIssued"}, allowSetters=true)
	private LoanCard loanCard;
	
	@Column(nullable = false)
	private Date cardIssueDate;
}
