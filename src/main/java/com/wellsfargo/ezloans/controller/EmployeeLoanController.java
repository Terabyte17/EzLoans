package com.wellsfargo.ezloans.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.service.EmployeeLoanService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeLoanController {
	
	@Autowired
	private EmployeeLoanService els;
	
	@ResponseBody
	@PostMapping("/applyLoan")
	public EmployeeLoan applyLoan(@Validated @RequestBody EmployeeLoan el) throws Exception {
		EmployeeLoan loanReceipt = els.applyLoan(el);
		return loanReceipt;
	}
	
	@Validated
	@GetMapping("/allLoans/{id}")
	public Set<EmployeeLoan> getAllLoans(@PathVariable String id) {
		return els.viewAllEmployeeLoans(id);
	}
}
