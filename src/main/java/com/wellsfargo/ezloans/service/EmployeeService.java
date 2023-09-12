package com.wellsfargo.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository emp_repo;
	
	public Employee saveEmployee(Employee e) {
		return emp_repo.save(e);
	}
	
	public List<Employee> listAll() {
		return emp_repo.findAll();
	}
	
}
