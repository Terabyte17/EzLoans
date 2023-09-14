package com.wellsfargo.ezloans.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.repository.EmployeeLoanRepository;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeLoanService {
	
	@Autowired
	private EmployeeLoanRepository empLoanRepo;
	
	@Autowired
	private LoanRepository loanRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public EmployeeLoan applyLoan(EmployeeLoan el) throws Exception {
		
		Optional<LoanCard> loanCard = loanRepo.findById(el.getLoanCard().getLoanId());
		Optional<Employee> emp = empRepo.findById(el.getEmp().getEmployeeId());
		
		if(!loanCard.isPresent() || !emp.isPresent()) {
			// specify exception type
			throw new Exception();
		}
		
		el.setEmp(emp.get());
		el.setLoanCard(loanCard.get());
		
		return empLoanRepo.save(el);
	}
	
	public Set<EmployeeLoan> viewAllEmployeeLoans(String empId) {
		return empLoanRepo.findByEmpEmployeeId(empId);
	}
}
