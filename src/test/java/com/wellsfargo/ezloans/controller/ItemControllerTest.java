package com.wellsfargo.ezloans.controller;
import com.wellsfargo.ezloans.controller.ItemController;
import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;
    private Item item;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        item = new Item();
        item.setIssueStatus(Boolean.TRUE);
        item.setItemMake("testMake");
        item.setItemCategory(Category.ElectronicGadgets);
    }

    @Test
    public void testSaveItem() {
        doNothing().when(itemService).saveItem(item);
        ResponseEntity<String> response = itemController.saveItem(item);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item saved successfully.", response.getBody());
    }

    @Test
    public void testSaveItemInternalServerError() {
        doThrow(RuntimeException.class).when(itemService).saveItem(item);
        ResponseEntity<String> response = itemController.saveItem(item);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while saving item.", response.getBody());
    }

    @Test
    public void testGetAllItems() {
        Optional<Boolean> issueStatus = Optional.empty();
        Optional<Category> itemCategory = Optional.empty();
        List<Item> items = new ArrayList<>();
        items.add(item);
        when(itemService.listAll(issueStatus, itemCategory)).thenReturn(items);

        ResponseEntity<?> response = itemController.getAllItems(issueStatus, itemCategory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());
    }

    @Test
    public void testGetAllItemsEmptyList() {
        Optional<Boolean> issueStatus = Optional.empty();
        Optional<Category> itemCategory = Optional.empty();
        List<Item> items = new ArrayList<>();
        when(itemService.listAll(issueStatus, itemCategory)).thenReturn(items);

        ResponseEntity<?> response = itemController.getAllItems(issueStatus, itemCategory);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllItemsInternalServerError() {
        Optional<Boolean> issueStatus = Optional.empty();
        Optional<Category> itemCategory = Optional.empty();
        doThrow(RuntimeException.class).when(itemService).listAll(issueStatus, itemCategory);
        ResponseEntity<?> response = itemController.getAllItems(issueStatus, itemCategory);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error while fetching items.", response.getBody());
    }

    @Test
    public void testUpdateItem() throws Exception {
        doNothing().when(itemService).updateItem(item);
        ResponseEntity<String> response = itemController.updateItem(item);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item updated successfully.", response.getBody());
    }

    @Test
    public void testUpdateItemNotFound() throws Exception {
        doThrow(RuntimeException.class).when(itemService).updateItem(item);
        ResponseEntity<String> response = itemController.updateItem(item);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Item ID. Updation failed.", response.getBody());
    }

    @Test
    public void testDeleteItem() throws Exception {
        doNothing().when(itemService).deleteItem(item);
        ResponseEntity<String> response = itemController.deleteItem(item);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item deleted successfully.", response.getBody());
    }

    @Test
    public void testDeleteItemNotFound() throws Exception {
        doThrow(RuntimeException.class).when(itemService).deleteItem(item);
        ResponseEntity<String> response = itemController.deleteItem(item);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Item ID. Deletion failed.", response.getBody());
    }
}
