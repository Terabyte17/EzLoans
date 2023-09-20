package com.wellsfargo.ezloans.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.model.Message;
import com.wellsfargo.ezloans.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService emp_service;
	
	@PostMapping("/login/user")
	public String loginUser(@Validated @RequestBody Map<String, Object> payload) throws ResourceNotFoundException {
		try {
			String username = (String) payload.get("username");
			String empId = emp_service.findByUsername(username);
			return empId;
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	@PostMapping("/users")
	public Message saveEmployee(@Validated @RequestBody Employee employee) {
		try {
			emp_service.saveEmployee(employee);
			return new Message("Employee added successfully.");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new Message("User with same Email ID exists.");
		}
	}
	
	@Validated
	@GetMapping("/users")
	public List<Employee> getAllEmployee() {
		return emp_service.listAll();
	}
	
	@PostMapping("/users/update")
	public Message updateEmployee(@Validated @RequestBody Employee employee) {
		try {
			emp_service.updateEmployee(employee);
			return new Message("Employee updated successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Employee Id. Updation failed.");
		}
	}
	
	@PostMapping("/users/delete")
	public Message deleteEmployee(@Validated @RequestBody Employee employee) {
		try {
			emp_service.deleteEmployee(employee);
			return new Message("Employee deleted successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Employee Id. Deletion failed.");
		}
	}
	
	@GetMapping("/users/items/{id}")
	public ResponseEntity<?> viewAllPurchasedItems(@Validated @PathVariable String id) {
		try {
			Set<ItemPurchase> items = emp_service.listAllItems(id);
			if (items.isEmpty()) {
				return new ResponseEntity<>("No items have been purchased by the user.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(items, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/loanCards/{id}")
	public ResponseEntity<?> viewAllLoanCards(@Validated @PathVariable String id) {
		try {
			Set<EmployeeLoan> loans = emp_service.listAllLoanCards(id);
			if (loans.isEmpty()) {
				return new ResponseEntity<>("No loan cards have been assigned to the user.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(loans, HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
