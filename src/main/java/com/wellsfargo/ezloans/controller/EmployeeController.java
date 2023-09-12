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
import com.wellsfargo.ezloans.service.EmployeeService;

@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService emp_service;
	
	@PostMapping("/users")
	public Employee saveProduct(@Validated @RequestBody Employee employee) {
		Employee emp = emp_service.saveEmployee(employee);
		return emp;
	}
	
	@Validated
	@GetMapping("/users")
	public List<Employee> getAllEmployee() {
		return emp_service.listAll();
	}
	
}
