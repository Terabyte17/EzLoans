package com.wellsfargo.ezloans.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
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
public class ItemPurchaseService {
	
	@Autowired
	private ItemPurchaseRepository itemPurchaseRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired 
	private LoanRepository loanRepo;
	
	@Autowired
	private EmployeeLoanRepository empLoanRepo;
	
	
	public void itemPurchased(ItemPurchase ip) throws Exception {
		
		Optional<Item> item = itemRepo.findById(ip.getItem().getItemId());
		Optional<Employee> emp = empRepo.findById(ip.getEmp().getEmployeeId());
		
		if(!item.isPresent() || !emp.isPresent()) {
			throw new ResourceNotFoundException("Invalid Item ID or Employee ID.");
		}
		
		if(item.get().getIssueStatus() == true) {
			throw new InvalidRequestException("Item has already been issued. Not in stock.");
		}
		
		Boolean alreadyHasLoanCard = false;
		Set<EmployeeLoan> empLoans = emp.get().getLoanCards();
		for (EmployeeLoan el:empLoans) {
			if (el.getLoanCard().getLoanType()==item.get().getItemCategory()) {
				alreadyHasLoanCard = true;
				break;
			}
		}
		
		if(!alreadyHasLoanCard) {
			Set<LoanCard> lcs = loanRepo.findByLoanType(item.get().getItemCategory());
			if (lcs.isEmpty()) {
				throw new ResourceNotFoundException("Loan Card of type " + item.get().getItemCategory() + " is not present currently.");
			}
			LoanCard lc = lcs.iterator().next();
			EmployeeLoan el = new EmployeeLoan();
			
			Date dateObj = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			el.setCardIssueDate(dateObj);
			el.setEmp(emp.get());
			el.setLoanCard(lc);
			empLoanRepo.save(el);
		}
		
		
		item.get().setIssueStatus(true);
		ip.setEmp(emp.get());
		ip.setItem(item.get());
		
		itemPurchaseRepo.save(ip);
		return;
	}
	
	public Set<ItemPurchase> viewAllItemsPurchased(String empId) {
		return itemPurchaseRepo.findByEmpEmployeeId(empId);
	}
}
