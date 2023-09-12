package com.wellsfargo.ezloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wellsfargo.ezloans.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
