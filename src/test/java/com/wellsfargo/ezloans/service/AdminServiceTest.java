package com.wellsfargo.ezloans.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.wellsfargo.ezloans.model.Admin;
import com.wellsfargo.ezloans.repository.AdminRepository;
public class AdminServiceTest {
    @InjectMocks
    private AdminService adminService;
    @Mock
    private AdminRepository adminRepository;
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
    public void testRegisterAdmin() {
        when(adminRepository.save(admin)).thenReturn(admin);
        Admin savedAdmin = adminService.registerAdmin(admin);
        assertNotNull(savedAdmin);
        assertEquals(username, savedAdmin.getUsername());
        verify(adminRepository, times(1)).save(admin);
    }
    @Test
    public void testLoginAdmin() {
        when(adminRepository.findByUsername(username)).thenReturn(Optional.of(admin));
        Optional<Admin> loggedInAdmin = adminService.loginAdmin(username);
        assertTrue(loggedInAdmin.isPresent());
        assertEquals(username, loggedInAdmin.get().getUsername());
        verify(adminRepository, times(1)).findByUsername(username);
    }
    @Test
    public void testLoginAdmin_NotFound() {
        when(adminRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());
        Optional<Admin> loggedInAdmin = adminService.loginAdmin("nonexistent");
        assertTrue(loggedInAdmin.isEmpty());
        verify(adminRepository, times(1)).findByUsername("nonexistent");
    }
}
