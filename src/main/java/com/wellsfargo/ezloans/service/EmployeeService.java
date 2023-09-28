package com.wellsfargo.ezloans.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.model.LoanCard;
import com.wellsfargo.ezloans.repository.EmployeeLoanRepository;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.ItemPurchaseRepository;
import com.wellsfargo.ezloans.repository.ItemRepository;
import com.wellsfargo.ezloans.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository emp_repo;
	
	@Autowired
	private ItemPurchaseRepository itemPurchaseRepo;
	
	@Autowired
	private EmployeeLoanRepository empLoanRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private LoanRepository loanRepo;
	
	public String findByUsername(String username) throws Exception {
		Optional<Employee> emp = emp_repo.findByAdminUsername(username);
		if(emp.isEmpty())
			throw new Exception("Invalid Username.");
		return emp.get().getEmployeeId();
	}
	
	public void saveEmployee(Employee e) throws Exception {
		emp_repo.save(e);
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
		
		Set<ItemPurchase> itemsPurchased = emp.get().getItemsPurchased();
		emp.get().setItemsPurchased(null);
		if (!itemsPurchased.isEmpty()) {
			for (ItemPurchase ip:itemsPurchased) {
				Item item = ip.getItem();
				item.setItemPurchased(null);
				itemRepo.save(item);
				itemPurchaseRepo.delete(ip);
			}
		}
		
		Set<EmployeeLoan> empLoans = emp.get().getLoanCards();
		emp.get().setLoanCards(null);
		if (!empLoans.isEmpty()) {
			for (EmployeeLoan el:empLoans) {
				LoanCard lc = el.getLoanCard();
				lc.removeEmployee(el);
				loanRepo.save(lc);
				empLoanRepo.delete(el);
			}
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
