package com.wellsfargo.ezloans.service;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.*;
import com.wellsfargo.ezloans.repository.EmployeeLoanRepository;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeLoanServiceTest {

    @InjectMocks
    private EmployeeLoanService employeeLoanService;

    @Mock
    private EmployeeLoanRepository empLoanRepo;

    @Mock
    private LoanRepository loanRepo;

    @Mock
    private EmployeeRepository empRepo;
    private Employee employee;
    private LoanCard loanCard;
    private EmployeeLoan employeeLoan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setEmployeeName("testName");
        employee.setDesignation("designation");
        employee.setDepartment("department");
        employee.setGender(Gender.Male);
        employee.setDob(new Date());
        employee.setDoj(new Date());
        employee.setEmail("test@test.com");

        loanCard = new LoanCard();
        loanCard.setLoanType(Category.ElectronicGadgets);
        loanCard.setDurationInYears(10);

        employeeLoan = new EmployeeLoan();
        employeeLoan.setCardIssueDate(new Date());
        employeeLoan.setLoanCard(loanCard);
        employeeLoan.setEmp(employee);
    }

    @Test
    public void testApplyLoan() throws Exception {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.of(loanCard));
        when(empRepo.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));

        employeeLoanService.applyLoan(employeeLoan);
        assertEquals(employee, employeeLoan.getEmp());
        assertEquals(loanCard, employeeLoan.getLoanCard());
        verify(empLoanRepo, times(1)).save(employeeLoan);
    }

    @Test
    public void testApplyLoanInvalidLoanCard() throws Exception {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.empty());
        when(empRepo.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));

        try {
            employeeLoanService.applyLoan(employeeLoan);
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Loan Card Id or Employee ID is invalid.", e.getMessage());
        }
    }

    @Test
    public void testApplyLoanInvalidEmployee() {
        when(loanRepo.findById(loanCard.getLoanId())).thenReturn(Optional.of(loanCard));
        when(empRepo.findById(employee.getEmployeeId())).thenReturn(Optional.empty());

        try {
            employeeLoanService.applyLoan(employeeLoan);
        } catch (Exception e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Loan Card Id or Employee ID is invalid.", e.getMessage());
        }
    }

    @Test
    public void testViewAllEmployeeLoans() {
        String employeeId = "emp123";
        Set<EmployeeLoan> employeeLoans = Set.of(employeeLoan);

        when(empLoanRepo.findByEmpEmployeeId(employeeId)).thenReturn(employeeLoans);

        Set<EmployeeLoan> result = employeeLoanService.viewAllEmployeeLoans(employeeId);

        assertEquals(employeeLoans, result);
    }
}
