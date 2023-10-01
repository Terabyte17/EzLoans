package com.wellsfargo.ezloans.controller;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.service.EmployeeLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeLoanControllerTest {

    @InjectMocks
    private EmployeeLoanController employeeLoanController;

    @Mock
    private EmployeeLoanService employeeLoanService;
    private EmployeeLoan employeeLoan;
    //create employeeLoan
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeLoan = new EmployeeLoan();
        employeeLoan.setCardIssueDate(new Date());;
    }

    @Test
    public void testApplyLoan() throws Exception {
        doNothing().when(employeeLoanService).applyLoan(employeeLoan);
        ResponseEntity<String> response = employeeLoanController.applyLoan(employeeLoan);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Applied for Loan Successfully.", response.getBody());
    }

    @Test
    public void testApplyLoanResourceNotFoundException() throws Exception {
        doThrow(ResourceNotFoundException.class).when(employeeLoanService).applyLoan(employeeLoan);
        ResponseEntity<String> response = employeeLoanController.applyLoan(employeeLoan);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testApplyLoanInternalServerError() throws Exception {
        doThrow(RuntimeException.class).when(employeeLoanService).applyLoan(employeeLoan);
        ResponseEntity<String> response = employeeLoanController.applyLoan(employeeLoan);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while applying for loan.", response.getBody());
    }

    @Test
    public void testGetAllLoans() {
        String employeeId = "123";
        EmployeeLoan employeeLoan = new EmployeeLoan();
        Set<EmployeeLoan> employeeLoans = new HashSet<>();
        employeeLoans.add(employeeLoan);
        when(employeeLoanService.viewAllEmployeeLoans(employeeId)).thenReturn(employeeLoans);

        ResponseEntity<?> response = employeeLoanController.getAllLoans(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeLoans, response.getBody());
    }

    @Test
    public void testGetAllLoansEmptySet() {
        String employeeId = "123";
        Set<EmployeeLoan> employeeLoans = new HashSet<>();
        when(employeeLoanService.viewAllEmployeeLoans(employeeId)).thenReturn(employeeLoans);

        ResponseEntity<?> response = employeeLoanController.getAllLoans(employeeId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllLoansInternalServerError() {
        String employeeId = "123";
        when(employeeLoanService.viewAllEmployeeLoans(employeeId)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = employeeLoanController.getAllLoans(employeeId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while fetching loans.", response.getBody());
    }
}
