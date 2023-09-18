package com.wellsfargo.ezloans.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

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
	
	public void saveEmployee(Employee e) throws Exception {
		try {
			emp_repo.save(e);
		}
		catch (Exception ex) {
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
	
}
