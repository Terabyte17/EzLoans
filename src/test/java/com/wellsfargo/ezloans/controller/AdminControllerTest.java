package com.wellsfargo.ezloans.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Admin;
import com.wellsfargo.ezloans.service.AdminService;
public class AdminControllerTest {
    @InjectMocks
    private AdminController adminController;
    @Mock
    private AdminService adminService;
    private Admin admin;
    private static final String username = "UserName";
    private static final String password = "abcd1234";
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEnabled(true);
        admin.setRole("ROLE_ADMIN");
    }
    @Test
    public void testCreateAdmin_Success() {
        when(adminService.registerAdmin(admin)).thenReturn(admin);
        ResponseEntity<String> response = adminController.createAdmin(admin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration Successful!", response.getBody());
        verify(adminService, times(1)).registerAdmin(admin);
    }
    @Test
    public void testCreateAdmin_Failure() {
        when(adminService.registerAdmin(admin)).thenReturn(null);
        ResponseEntity<String> response = adminController.createAdmin(admin);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Registration Failed!", response.getBody());
        verify(adminService, times(1)).registerAdmin(admin);
    }
    @Test
    public void testCreateAdmin_Exception(){
        when(adminService.registerAdmin(admin)).thenThrow(RuntimeException.class);
        ResponseEntity<String> response = adminController.createAdmin(admin);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: null", response.getBody());
        verify(adminService, times(1)).registerAdmin(admin);
    }
    @Test
    public void testLoginAdmin_Success() throws ResourceNotFoundException {
        when(adminService.loginAdmin(username)).thenReturn(Optional.of(new Admin()));
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        String result = adminController.loginAdmin(payload);
        assertNotNull(result);
        verify(adminService, times(1)).loginAdmin(username);
    }
    @Test
    public void testLoginAdmin_NotFound() throws ResourceNotFoundException {
        when(adminService.loginAdmin("nonexistent")).thenReturn(Optional.empty());
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "nonexistent");
        String result = adminController.loginAdmin(payload);
        assertNull(result);
        verify(adminService, times(1)).loginAdmin("nonexistent");
    }
    @Test
    public void testLoginAdmin_Exception() throws ResourceNotFoundException{
        when(adminService.loginAdmin(username)).thenThrow(RuntimeException.class);
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        String result = adminController.loginAdmin(payload);
        assertNull(result);
        verify(adminService, times(1)).loginAdmin(username);
    }
}
