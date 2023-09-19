package com.wellsfargo.ezloans.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepo;
	
	public LoanCard saveLoanCard(LoanCard l) {
		return loanRepo.save(l);
	}
	
	public List<LoanCard> listAll() {
		return loanRepo.findAll();
	}
	
	public void updateLoan(LoanCard l) throws Exception {
		Optional<LoanCard> loan = loanRepo.findById(l.getLoanId());
		if(loan.isEmpty()) {
			throw new Exception("Invalid Loan Id");
		}
		loanRepo.save(l);
		return;
	}
	
	public void deleteLoan(LoanCard l) throws Exception {
		Optional<LoanCard> loan = loanRepo.findById(l.getLoanId());
		if(loan.isEmpty()) {
			throw new Exception("Invalid Loan Id");
		}
		loanRepo.deleteById(l.getLoanId());
		return;
	}
}
