package com.wellsfargo.ezloans.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

import com.wellsfargo.ezloans.model.EmployeeLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeLoanRepositoryTest {

    @Autowired
    private EmployeeLoanRepository employeeLoanRepository;
    private EmployeeLoan employeeLoan;
    private static final long loanIssueId = 1l;

    @BeforeEach
    public void setUp() {
        employeeLoanRepository.deleteAll();

        employeeLoan = new EmployeeLoan();
    }

    @Test
    public void testFindByEmpEmployeeId() {
        // Save the EmployeeLoan to the database
        employeeLoanRepository.save(employeeLoan);

        // Call the repository's findByEmpEmployeeId method
        Set<EmployeeLoan> result = employeeLoanRepository.findByEmpEmployeeId("E123");

        // Assert that the result contains the expected EmployeeLoan object
        assertTrue(result.contains(employeeLoan));
    }

    @Test
    public void testFindByLoanCardLoanId() {
        employeeLoanRepository.save(employeeLoan);

        // Call the repository's findByLoanCardLoanId method
        Set<EmployeeLoan> result = employeeLoanRepository.findByLoanCardLoanId("L456");

        // Assert that the result contains the expected EmployeeLoan object
        assertTrue(result.contains(employeeLoan));
    }

}
