package com.wellsfargo.ezloans.service;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.*;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    private Employee employee;
    private static final String employeeId = "TestId";
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        ItemPurchase itemPurchase = new ItemPurchase();
        itemPurchase.setPurchaseDate(new Date());

        LoanCard loanCard = new LoanCard();
        loanCard.setLoanType(Category.ElectronicGadgets);
        loanCard.setDurationInYears(10);

        EmployeeLoan employeeLoan = new EmployeeLoan();
        employeeLoan.setCardIssueDate(new Date());
        employeeLoan.setLoanCard(loanCard);
        employeeLoan.setEmp(employee);

        employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName("testName");
        employee.setDesignation("designation");
        employee.setDepartment("department");
        employee.setGender(Gender.Male);
        employee.setDob(new Date());
        employee.setDoj(new Date());
        employee.setEmail("test@test.com");
        employee.setItemsPurchased(Set.of(itemPurchase));
        employee.setLoanCards(Set.of(employeeLoan));

        //mock behaviour
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
    }

    @Test
    public void testFindByUsername() throws Exception{
        String username = "User1";
        when(employeeRepository.findByAdminUsername(username)).thenReturn(Optional.of(employee));

        String result = employeeService.findByUsername(username);
        assertEquals(result, employeeId);

    }
    @Test
    public void testFindbyUsernameNotFound(){
        String username = "User1";
        when(employeeRepository.findByAdminUsername(username)).thenReturn(Optional.empty());
        try{
            employeeService.findByUsername(username);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Username.", e.getMessage());
        }
    }
    @Test
    public void testUpdateEmployee() throws Exception{
        employeeService.updateEmployee(employee);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployeeException(){
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        try{
            employeeService.updateEmployee(employee);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Employee Id.", e.getMessage());
        }
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(0)).save(employee);
    }
    @Test
    public void testDeleteEmployee() throws Exception{
        doNothing().when(employeeRepository).deleteById(employeeId);
        employeeService.deleteEmployee(employee);
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    public void testDeleteEmployeeException(){
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        try{
            employeeService.deleteEmployee(employee);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Employee Id.", e.getMessage());
        }
        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(0)).deleteById(employeeId);
    }
    @Test
    public void testListAllItems() throws Exception{
        Set<ItemPurchase> result = employeeService.listAllItems(employeeId);
        assertEquals(1 , result.size());
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    public void testListAllItemsException(){
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        try{
            employeeService.listAllItems(employeeId);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Employee Id.", e.getMessage());
        }
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    public void testListAllLoanCards() throws Exception{
        Set<EmployeeLoan> result = employeeService.listAllLoanCards(employeeId);
        assertEquals(1 , result.size());
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    public void testListAllLoanCardsException(){
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        try{
            employeeService.listAllLoanCards(employeeId);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Employee Id.", e.getMessage());
        }
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    public void testSaveEmployee() throws Exception{
        employeeService.saveEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }
    @Test
    public void testListAll() throws Exception{
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<Employee> result = employeeService.listAll();
        assertEquals(1, result.size());
    }
}