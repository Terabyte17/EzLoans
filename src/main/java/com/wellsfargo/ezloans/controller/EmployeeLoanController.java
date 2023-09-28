package com.wellsfargo.ezloans.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.service.EmployeeLoanService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeLoanController {
	
	@Autowired
	private EmployeeLoanService els;
	
	@ResponseBody
	@PostMapping("/applyLoan")
	public ResponseEntity<String> applyLoan(@Validated @RequestBody EmployeeLoan el) throws Exception {
		try {
			els.applyLoan(el);
			return new ResponseEntity<>("Applied for Loan Successfully.", HttpStatus.OK);
		} 
		catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while applying for loan.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Validated
	@GetMapping("/allLoans/{id}")
	public ResponseEntity<?> getAllLoans(@PathVariable String id) {
		try {
			Set<EmployeeLoan> empLoans = els.viewAllEmployeeLoans(id);
			if(empLoans.isEmpty())
				return new ResponseEntity<>("No loans have been assigned yet.", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(empLoans, HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while fetching loans.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
