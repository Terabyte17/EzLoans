package com.wellsfargo.ezloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.LoanCard;

public interface LoanRepository extends JpaRepository<LoanCard, String> {
	
}
