package com.wellsfargo.ezloans.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.repository.EmployeeLoanRepository;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.ItemPurchaseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ItemPurchaseRepository itemPurchaseRepo;
	
	@Autowired
	private EmployeeLoanRepository empLoanRepo;
	
	public String findByUsername(String username) throws Exception {
		Optional<Employee> emp = empRepo.findByAdminUsername(username);
		if(emp.isEmpty())
			throw new ResourceNotFoundException("Invalid Username.");
		return emp.get().getEmployeeId();
	}
	
	public void saveEmployee(Employee e) throws Exception {
		empRepo.save(e);
	}
	
	public List<Employee> listAll() {
		return empRepo.findAll();
	}
	
	public void updateEmployee(Employee e) throws Exception {
		Optional<Employee> emp = empRepo.findById(e.getEmployeeId());
		if(emp.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Employee Id.");
		}
		empRepo.save(e);
	}
	
	public void deleteEmployee(Employee e) throws Exception {
		Optional<Employee> emp = empRepo.findById(e.getEmployeeId());
		if(emp.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Employee Id.");
		}
		
		
		
//		Set<ItemPurchase> itemsPurchased = emp.get().getItemsPurchased();
//		if (!itemsPurchased.isEmpty()) {
//			for(ItemPurchase itemPurchase:itemsPurchased)
//				itemPurchaseRepo.deleteById(itemPurchase.getIssueId());
//		}
//		
//		
//		Set<EmployeeLoan> empLoans = emp.get().getLoanCards();
//		if(!empLoans.isEmpty()) {
//			for(EmployeeLoan empLoan:empLoans)
//				empLoanRepo.deleteById(empLoan.getLoanIssueId());
//		}
		
		empRepo.deleteById(e.getEmployeeId());
	}
	
	public Set<ItemPurchase> listAllItems(String id) throws Exception {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Employee Id.");
		}
		Set<ItemPurchase> items = emp.get().getItemsPurchased();
		return items;
	}
	
	public Set<EmployeeLoan> listAllLoanCards(String id) throws Exception {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Employee Id.");
		}
		Set<EmployeeLoan> loanCards = emp.get().getLoanCards();
		return loanCards;
	}
}
