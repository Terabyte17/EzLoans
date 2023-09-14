package com.wellsfargo.ezloans.service;

import java.util.List;

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
}
