package com.wellsfargo.ezloans.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LoanCard {
	
	@Id
	@UuidGenerator
	private String loanId;
	
	@Column(nullable = false)
	private Category loanType;
	
	@Column(nullable = false)
	private Integer durationInYears;
	
	@Column(nullable = false)
	private Date issueDate;
	
	@OneToMany(mappedBy="loanCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<EmployeeLoan> employeesIssued;

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Category getLoanType() {
		return loanType;
	}

	public void setLoanType(Category loanType) {
		this.loanType = loanType;
	}

	public Integer getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(Integer durationInYears) {
		this.durationInYears = durationInYears;
	}

	public Set<EmployeeLoan> getEmployeesIssued() {
		return employeesIssued;
	}

	public void setEmployeesIssued(Set<EmployeeLoan> employeesIssued) {
		this.employeesIssued = employeesIssued;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public void removeEmployee(EmployeeLoan el) {
		this.employeesIssued.remove(el);
	}
	
}
