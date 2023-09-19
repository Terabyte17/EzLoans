package com.wellsfargo.ezloans.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository emp_repo;
	
	public void saveEmployee(Employee e) throws Exception {
		try {
			emp_repo.save(e);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception();
		}
		return;
	}
	
	public List<Employee> listAll() {
		return emp_repo.findAll();
	}
	
	public void updateEmployee(Employee e) throws Exception {
		Optional<Employee> emp = emp_repo.findById(e.getEmployeeId());
		if(emp.isEmpty()) {
			throw new Exception("Invalid Employee Id.");
		}
		emp_repo.save(e);
		return;
	}
	
	public void deleteEmployee(Employee e) throws Exception {
		Optional<Employee> emp = emp_repo.findById(e.getEmployeeId());
		if(emp.isEmpty()) {
			throw new Exception("Invalid Employee Id.");
		}
		emp_repo.deleteById(e.getEmployeeId());
		return;
	}
	
	public Set<ItemPurchase> listAllItems(String id) throws Exception {
		Optional<Employee> emp = emp_repo.findById(id);
		if(emp.isEmpty()) {
			throw new Exception("Invalid Employee Id.");
		}
		Set<ItemPurchase> items = emp.get().getItemsPurchased();
		return items;
	}
	
	public Set<EmployeeLoan> listAllLoanCards(String id) throws Exception {
		Optional<Employee> emp = emp_repo.findById(id);
		if(emp.isEmpty()) {
			throw new Exception("Invalid Employee Id.");
		}
		Set<EmployeeLoan> loanCards = emp.get().getLoanCards();
		return loanCards;
	}
}
