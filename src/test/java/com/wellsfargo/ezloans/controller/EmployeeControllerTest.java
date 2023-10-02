package com.wellsfargo.ezloans.controller;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.EmployeeLoan;
import com.wellsfargo.ezloans.model.Gender;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;
    private Employee employee;

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
    }

    @Test
    public void testLoginUser() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "testUser");

        when(employeeService.findByUsername("testUser")).thenReturn("123");

        String result = employeeController.loginUser(payload);
        assertEquals("123", result);
    }

    @Test
    public void testLoginUserResourceNotFoundException() throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "nonExistingUser");

        when(employeeService.findByUsername("nonExistingUser")).thenThrow(ResourceNotFoundException.class);
        String result = employeeController.loginUser(payload);
        assertNull(result);
    }

    @Test
    public void testSaveEmployee() throws Exception {
        doNothing().when(employeeService).saveEmployee(employee);
        ResponseEntity<String> response = employeeController.saveEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee added successfully.", response.getBody());
    }

    @Test
    public void testSaveEmployeeDataIntegrityViolationException() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(employeeService).saveEmployee(employee);
        ResponseEntity<String> response = employeeController.saveEmployee(employee);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User with same Email ID exists.", response.getBody());
    }
    @Test
    public void testSaveEmployeeException() throws Exception{
        doThrow(RuntimeException.class).when(employeeService).saveEmployee(employee);
        ResponseEntity<String> response = employeeController.saveEmployee(employee);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while saving employee.", response.getBody());
    }

    @Test
    public void testGetAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeService.listAll()).thenReturn(employees);

        ResponseEntity<?> response = employeeController.getAllEmployee();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        assertFalse(((List<?>) response.getBody()).isEmpty());
    }
    @Test
    public void testGetAllEmployeeNoEmployee(){
        List<Employee> emptyList = new ArrayList<>();
        when(employeeService.listAll()).thenReturn(emptyList);
        ResponseEntity<?> response = employeeController.getAllEmployee();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No Employee Found.", response.getBody());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        doNothing().when(employeeService).updateEmployee(employee);
        ResponseEntity<String> response = employeeController.updateEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee updated successfully.", response.getBody());
    }

    @Test
    public void testUpdateEmployeeNotFound() throws Exception {
        doThrow(RuntimeException.class).when(employeeService).updateEmployee(employee);
        ResponseEntity<String> response = employeeController.updateEmployee(employee);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Employee Id. Updation failed.", response.getBody());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(employee);
        ResponseEntity<String> response = employeeController.deleteEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee deleted successfully.", response.getBody());
    }

    @Test
    public void testDeleteEmployeeNotFound() throws Exception {
        doThrow(RuntimeException.class).when(employeeService).deleteEmployee(employee);
        ResponseEntity<String> response = employeeController.deleteEmployee(employee);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Employee Id. Deletion failed.", response.getBody());
    }

    @Test
    public void testViewAllPurchasedItems() throws Exception {
        String userId = "123";
        Set<ItemPurchase> items = new HashSet<>();
        items.add(new ItemPurchase());

        when(employeeService.listAllItems(userId)).thenReturn(items);

        ResponseEntity<?> response = employeeController.viewAllPurchasedItems(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Set);
        assertFalse(((Set<?>) response.getBody()).isEmpty());
    }

    @Test
    public void testViewAllPurchasedItemsNotFound() throws Exception {
        String userId = "123";

        when(employeeService.listAllItems(userId)).thenReturn(new HashSet<>());

        ResponseEntity<?> response = employeeController.viewAllPurchasedItems(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No items have been purchased by the user.", response.getBody());
    }
    @Test
    public void testViewAllPurchasedItemsException() throws Exception {
        String userId = "123";
        when(employeeService.listAllItems(userId)).thenThrow(RuntimeException.class);

        ResponseEntity<?> response = employeeController.viewAllPurchasedItems(userId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testViewAllLoanCards() throws Exception {
        String userId = "123";
        Set<EmployeeLoan> loans = new HashSet<>();
        loans.add(new EmployeeLoan());

        when(employeeService.listAllLoanCards(userId)).thenReturn(loans);

        ResponseEntity<?> response = employeeController.viewAllLoanCards(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Set);
        assertFalse(((Set<?>) response.getBody()).isEmpty());
    }

    @Test
    public void testViewAllLoanCardsNotFound() throws Exception {
        String userId = "123";

        when(employeeService.listAllLoanCards(userId)).thenReturn(new HashSet<>());

        ResponseEntity<?> response = employeeController.viewAllLoanCards(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No loan cards have been assigned to the user.", response.getBody());
    }
    @Test
    public void testViewAllLoanCardsException() throws Exception {
        String userId = "123";
        when(employeeService.listAllLoanCards(userId)).thenThrow(RuntimeException.class);
        ResponseEntity<?> response = employeeController.viewAllLoanCards(userId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

