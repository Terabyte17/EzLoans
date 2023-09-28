package com.wellsfargo.ezloans.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.LoanCard;

public interface LoanRepository extends JpaRepository<LoanCard, String> {
	public Set<LoanCard> findByLoanType(Category loanType);
}
