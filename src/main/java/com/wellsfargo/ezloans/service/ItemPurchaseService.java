package com.wellsfargo.ezloans.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.ItemPurchaseRepository;
import com.wellsfargo.ezloans.repository.ItemRepository;

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
	
	public void itemPurchased(ItemPurchase ip) throws Exception {
		
		Optional<Item> item = itemRepo.findById(ip.getItem().getItemId());
		Optional<Employee> emp = empRepo.findById(ip.getEmp().getEmployeeId());
		
		System.out.println(item.isPresent());
		System.out.println(emp.isPresent());
		if(!item.isPresent() || !emp.isPresent()) {
			throw new ResourceNotFoundException("Invalid Item ID or Employee ID.");
		}
		
		if(item.get().getIssueStatus() == true) {
			throw new InvalidRequestException("Item has already been issued. Not in stock.");
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
