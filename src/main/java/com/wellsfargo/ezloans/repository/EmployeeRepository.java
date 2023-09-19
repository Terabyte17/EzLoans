package com.wellsfargo.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.ezloans.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	@Query("select new Employee(e.employeeId,e.employeeName,e.designation,e.department,e.gender,e.dob,e.doj,e.email) from Employee e")
	List<Employee> findAll();
}
