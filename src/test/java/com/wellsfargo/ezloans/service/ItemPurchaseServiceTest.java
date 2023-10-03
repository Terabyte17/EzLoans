package com.wellsfargo.ezloans.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Employee;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.repository.EmployeeRepository;
import com.wellsfargo.ezloans.repository.ItemPurchaseRepository;
import com.wellsfargo.ezloans.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemPurchaseServiceTest {

    @InjectMocks
    private ItemPurchaseService itemPurchaseService;

    @Mock
    private ItemPurchaseRepository itemPurchaseRepo;

    @Mock
    private ItemRepository itemRepo;

    @Mock
    private EmployeeRepository empRepo;

    private ItemPurchase itemPurchase;
    private Item item;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize objects
        itemPurchase = new ItemPurchase();
        item = new Item();
        item.setIssueStatus(Boolean.FALSE);
        employee = new Employee();
        item.setItemId("item123");
        employee.setEmployeeId("emp456");
        itemPurchase.setItem(item);
        itemPurchase.setEmp(employee);

        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.of(item));
        when(empRepo.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
    }

    @Test
    public void testItemPurchased_ValidItemAndEmployee() throws Exception {
        itemPurchaseService.itemPurchased(itemPurchase);

        assertTrue(item.getIssueStatus());
        assertEquals(employee, itemPurchase.getEmp());
        assertEquals(item, itemPurchase.getItem());
        verify(itemRepo, times(1)).findById(item.getItemId());
        verify(empRepo, times(1)).findById(employee.getEmployeeId());
        verify(itemPurchaseRepo, times(1)).save(itemPurchase);
    }
    @Test
    public void testItemPurchased_InvalidItem() {
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> itemPurchaseService.itemPurchased(itemPurchase));
        assertFalse(item.getIssueStatus());
        verify(itemRepo, times(1)).findById(item.getItemId());
        verify(empRepo, times(1)).findById(employee.getEmployeeId());
        verify(itemPurchaseRepo, never()).save(itemPurchase);
    }

    @Test
    public void testItemPurchased_InvalidEmployee() {
        when(empRepo.findById(employee.getEmployeeId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> itemPurchaseService.itemPurchased(itemPurchase));
        assertFalse(item.getIssueStatus());
        verify(itemRepo, times(1)).findById(item.getItemId());
        verify(empRepo, times(1)).findById(employee.getEmployeeId());
        verify(itemPurchaseRepo, never()).save(itemPurchase);
    }
    @Test
    public void testItemPurchased_ItemAlreadyIssued() {
        item.setIssueStatus(true);
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.of(item));
        assertThrows(InvalidRequestException.class, () -> itemPurchaseService.itemPurchased(itemPurchase));
        assertTrue(item.getIssueStatus());
        verify(itemRepo, times(1)).findById(item.getItemId());
        verify(empRepo, times(1)).findById(employee.getEmployeeId());
        verify(itemPurchaseRepo, never()).save(itemPurchase);
    }
    @Test
    public void testViewAllItemsPurchased() {
        String empId = "emp123";
        Set<ItemPurchase> itemPurchases = new HashSet<>();
        itemPurchases.add(itemPurchase);

        when(itemPurchaseRepo.findByEmpEmployeeId(empId)).thenReturn(itemPurchases);
        Set<ItemPurchase> result = itemPurchaseService.viewAllItemsPurchased(empId);
        assertEquals(itemPurchases, result);
        verify(itemPurchaseRepo, times(1)).findByEmpEmployeeId(empId);
    }
}
