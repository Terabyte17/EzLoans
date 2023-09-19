package com.wellsfargo.ezloans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.model.Message;
import com.wellsfargo.ezloans.service.LoanService;

@RestController
@RequestMapping("/api")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("/loans")
	public LoanCard saveLoanCard(@Validated @RequestBody LoanCard loan) {
		LoanCard loanCard = loanService.saveLoanCard(loan);
		return loanCard;
	}
	
	@Validated
	@GetMapping("/loans")
	public List<LoanCard> getAllLoans() {
		return loanService.listAll();
	}
	
	@PostMapping("/loans/update")
	@ResponseBody
	public Message updateLoan(@Validated @RequestBody LoanCard loan) {
		try {
			loanService.updateLoan(loan);
			return new Message("Loan Card updated successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Loan Card ID. Updation failed.");
		}
	}
	
	@PostMapping("/loans/delete")
	@ResponseBody
	public Message deleteLoanCard(@Validated @RequestBody LoanCard loan) {
		try {
			loanService.deleteLoan(loan);
			return new Message("Loan Card deleted successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Loan ID. Deletion failed.");
		}
	}
}
