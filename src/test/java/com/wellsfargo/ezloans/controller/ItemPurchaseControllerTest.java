package com.wellsfargo.ezloans.controller;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.service.ItemPurchaseService;
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

public class ItemPurchaseControllerTest {

    @InjectMocks
    private ItemPurchaseController itemPurchaseController;

    @Mock
    private ItemPurchaseService itemPurchaseService;
    private ItemPurchase itemPurchase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemPurchase = new ItemPurchase();
        itemPurchase.setPurchaseDate(new Date());
            }

    @Test
    public void testPurchaseItem() throws Exception {
        doNothing().when(itemPurchaseService).itemPurchased(itemPurchase);

        ResponseEntity<String> response = itemPurchaseController.purchaseItem(itemPurchase);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully purchased item.", response.getBody());
    }

    @Test
    public void testPurchaseItemResourceNotFoundException() throws Exception {
        doThrow(ResourceNotFoundException.class).when(itemPurchaseService).itemPurchased(itemPurchase);

        ResponseEntity<String> response = itemPurchaseController.purchaseItem(itemPurchase);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testPurchaseItemInvalidRequestException() throws Exception {
        doThrow(InvalidRequestException.class).when(itemPurchaseService).itemPurchased(itemPurchase);

        ResponseEntity<String> response = itemPurchaseController.purchaseItem(itemPurchase);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testPurchaseItemInternalServerError() throws Exception {
        doThrow(RuntimeException.class).when(itemPurchaseService).itemPurchased(itemPurchase);

        ResponseEntity<String> response = itemPurchaseController.purchaseItem(itemPurchase);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while purchasing item.", response.getBody());
    }

    @Test
    public void testGetAllItems() {
        String employeeId = "123";
        Set<ItemPurchase> itemsPurchased = new HashSet<>();
        itemsPurchased.add(itemPurchase);
        when(itemPurchaseService.viewAllItemsPurchased(employeeId)).thenReturn(itemsPurchased);

        ResponseEntity<?> response = itemPurchaseController.getAllItems(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemsPurchased, response.getBody());
    }

    @Test
    public void testGetAllItemsEmptySet() {
        String employeeId = "123";
        Set<ItemPurchase> itemsPurchased = new HashSet<>();
        when(itemPurchaseService.viewAllItemsPurchased(employeeId)).thenReturn(itemsPurchased);

        ResponseEntity<?> response = itemPurchaseController.getAllItems(employeeId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllItemsInternalServerError() {
        String employeeId = "123";
        doThrow(RuntimeException.class).when(itemPurchaseService).viewAllItemsPurchased(employeeId);
        ResponseEntity<?> response = itemPurchaseController.getAllItems(employeeId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while fetching list of purchased items.", response.getBody());
    }
}
