package com.wellsfargo.ezloans.repository;

import com.wellsfargo.ezloans.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;
    private static final String username = "UserName";
    private static final String password = "abcd1234";
    private Admin admin;
    @BeforeEach
    public void setUp() {
        adminRepository.deleteAll();
        admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEnabled(true);
        admin.setRole("admin");
    }
    @Test
    public void testSaveAdmin() {
        adminRepository.save(admin);
        Admin savedAdmin = adminRepository.findById(admin.getId()).orElse(null);
        assertNotNull(savedAdmin);
        assertEquals(username, savedAdmin.getUsername());
    }
    @Test
    public void testFindByUsername() {
        adminRepository.save(admin);
        Optional<Admin> foundAdmin = adminRepository.findByUsername(username);
        assertTrue(foundAdmin.isPresent());
        assertEquals(username, foundAdmin.get().getUsername());
    }
    @Test
    public void testFindByUsername_NotFound() {
        Optional<Admin> foundAdmin = adminRepository.findByUsername("nonexistent");
        assertTrue(foundAdmin.isEmpty());
    }
    @Test
    public void testGetAdminByUsername() {
        adminRepository.save(admin);
        Admin foundAdmin = adminRepository.getAdminByUsername(username);
        assertNotNull(foundAdmin);
        assertEquals(username, foundAdmin.getUsername());
    }
}
