package com.wellsfargo.ezloans.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.EmployeeLoan;

public interface EmployeeLoanRepository extends JpaRepository<EmployeeLoan, Long> {
	
	public Set<EmployeeLoan> findByEmpEmployeeId(String eid);
	public Set<EmployeeLoan> findByLoanCardLoanId(String lid);
}
