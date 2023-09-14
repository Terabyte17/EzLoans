package com.wellsfargo.ezloans.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public ItemPurchase itemPurchased(ItemPurchase ip) throws Exception {
		
		Optional<Item> item = itemRepo.findById(ip.getItem().getItemId());
		Optional<Employee> emp = empRepo.findById(ip.getEmp().getEmployeeId());
		
		if(!item.isPresent() || !emp.isPresent()) {
			// specify exception type
			throw new Exception();
		}
		
		ip.setEmp(emp.get());
		ip.setItem(item.get());
		
		return itemPurchaseRepo.save(ip);
	}
	
	public Set<ItemPurchase> viewAllItemsPurchased(String empId) {
		return itemPurchaseRepo.findByEmpEmployeeId(empId);
	}
}
