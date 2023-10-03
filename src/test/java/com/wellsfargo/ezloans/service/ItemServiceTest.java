package com.wellsfargo.ezloans.service;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepo;

    private Item item;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        item = new Item();
        item.setItemId("TestId");
        item.setIssueStatus(Boolean.FALSE);
    }
    @Test
    public void testSaveItem(){
        itemService.saveItem(item);
        verify(itemRepo, times(1)).save(item);
    }
    @Test
    public void testListAllBothNull(){
        when(itemRepo.findAll()).thenReturn(List.of(item));
        List<Item> result = itemService.listAll(Optional.empty(), Optional.empty());
        assertEquals(1, result.size());
        verify(itemRepo, times(1)).findAll();
    }
    @Test
    public void testListAll_BothPresent(){
        Boolean status = Boolean.TRUE;
        Category category = Category.ElectronicGadgets;
        when(itemRepo.findByIssueStatusAndItemCategory(status, category)).thenReturn(List.of(item));
        List<Item> result = itemService.listAll(Optional.of(status), Optional.of(category));
        assertEquals(1, result.size());
        verify(itemRepo, times(1)).findByIssueStatusAndItemCategory(status, category);
    }
    @Test
    public void testListAll_NullIssueStatus(){
        Category category = Category.ElectronicGadgets;
        when(itemRepo.findByItemCategory(category)).thenReturn(List.of(item));
        List<Item> result = itemService.listAll(Optional.empty(), Optional.of(category));
        assertEquals(1, result.size());
        verify(itemRepo, times(1)).findByItemCategory(category);
    }
    @Test
    public void testListAll_NullItemMake(){
        Boolean status = Boolean.TRUE;
        when(itemRepo.findByIssueStatus(status)).thenReturn(List.of(item));
        List<Item> result = itemService.listAll(Optional.of(status), Optional.empty());
        assertEquals(1, result.size());
        verify(itemRepo, times(1)).findByIssueStatus(status);
    }
    // Tests for updateItem
    @Test
    public void testUpdateItem() throws Exception {
        Item updatedItem = new Item();
        updatedItem.setItemId("item123");
        updatedItem.setIssueStatus(true);

        when(itemRepo.findById(updatedItem.getItemId())).thenReturn(Optional.of(item));
        itemService.updateItem(updatedItem);
        verify(itemRepo, times(1)).findById(updatedItem.getItemId());
        verify(itemRepo, times(1)).save(updatedItem);
    }

    @Test
    public void testUpdateItemInvalidItemId() {
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.empty());
        try{
            itemService.updateItem(item);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Item Id", e.getMessage());
        }
        verify(itemRepo, times(1)).findById(item.getItemId());
        verifyNoMoreInteractions(itemRepo);
    }

    @Test
    public void testUpdateItemInvalidIssueStatusChange() {
        item.setIssueStatus(Boolean.TRUE);
        Item updatedItem = new Item();
        updatedItem.setItemId("item123");
        updatedItem.setIssueStatus(false);

        when(itemRepo.findById(updatedItem.getItemId())).thenReturn(Optional.of(item));
        try{
            itemService.updateItem(updatedItem);
        }catch(Exception e){
            assertEquals(InvalidRequestException.class, e.getClass());
            assertEquals("Item issued, can't change issue status directly. Need to return item.", e.getMessage());
        }
        verify(itemRepo, times(1)).findById(updatedItem.getItemId());
        verifyNoMoreInteractions(itemRepo);
    }
    //Tests for deleteItem
    @Test
    public void testDeleteItem() throws Exception {
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.of(item));
        itemService.deleteItem(item);
        verify(itemRepo, times(1)).findById(item.getItemId());
        verify(itemRepo, times(1)).deleteById(item.getItemId());
    }

    @Test
    public void testDeleteItemInvalidItemId() {
        when(itemRepo.findById(item.getItemId())).thenReturn(Optional.empty());
        try{
            itemService.deleteItem(item);
        }catch(Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("Invalid Item Id", e.getMessage());
        }
        verify(itemRepo, times(1)).findById(item.getItemId());
        verifyNoMoreInteractions(itemRepo);
    }
}