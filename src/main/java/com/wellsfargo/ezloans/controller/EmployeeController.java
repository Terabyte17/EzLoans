package com.wellsfargo.ezloans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.Message;
import com.wellsfargo.ezloans.service.EmployeeService;

@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService emp_service;
	
	@PostMapping("/users")
	public Message saveEmployee(@Validated @RequestBody Employee employee) {
		try {
			emp_service.saveEmployee(employee);
			return new Message("Employee added successfully.");
		}
		catch (Exception ex) {
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
}
