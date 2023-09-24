package com.wellsfargo.ezloans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.service.LoanService;

@RestController
@RequestMapping("/api")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@PostMapping("/loans")
	public ResponseEntity<String> saveLoanCard(@Validated @RequestBody LoanCard loan) {
		try {
			loanService.saveLoanCard(loan);
			return new ResponseEntity<>("Successfully saved loan card.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while saving loan card.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Validated
	@GetMapping("/loans")
	public ResponseEntity<?> getAllLoans() {
		try {
			List<LoanCard> loans = loanService.listAll();
			if (loans.isEmpty())
				return new ResponseEntity<>("No loan cards found", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(loans, HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while fetching Loan Cards.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loans/update")
	@ResponseBody
	public ResponseEntity<String> updateLoan(@Validated @RequestBody LoanCard loan) {
		try {
			loanService.updateLoan(loan);
			return new ResponseEntity<>("Loan Card updated successfully.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Invalid Loan Card ID. Updation failed.", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/loans/delete")
	@ResponseBody
	public ResponseEntity<String> deleteLoanCard(@Validated @RequestBody LoanCard loan) {
		try {
			loanService.deleteLoan(loan);
			return new ResponseEntity<>("Loan Card deleted successfully.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Invalid Loan ID. Deletion failed.", HttpStatus.NOT_FOUND);
		}
	}
}
