package com.wellsfargo.ezloans.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.wellsfargo.ezloans.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class AdminDetailsTest {
    private static final String username = "UserName";
    private static final String password = "abcd1234";
    private Admin admin;
    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEnabled(true);
        admin.setRole("ROLE_ADMIN");
    }

    @Test
    public void testGetAuthorities() {
        AdminDetails adminDetails = new AdminDetails(admin);
        var authorities = adminDetails.getAuthorities();
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    public void testGetPassword() {
        AdminDetails adminDetails = new AdminDetails(admin);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertTrue(encoder.matches(password, adminDetails.getPassword()));
    }

    @Test
    public void testGetUsername() {
        AdminDetails adminDetails = new AdminDetails(admin);
        assertEquals(username, adminDetails.getUsername());
    }

    @Test
    public void testAccountNonExpired() {
        AdminDetails adminDetails = new AdminDetails(new Admin());
        assertTrue(adminDetails.isAccountNonExpired());
    }

    @Test
    public void testAccountNonLocked() {
        AdminDetails adminDetails = new AdminDetails(new Admin());
        assertTrue(adminDetails.isAccountNonLocked());
    }

    @Test
    public void testCredentialsNonExpired() {
        AdminDetails adminDetails = new AdminDetails(new Admin());
        assertTrue(adminDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        AdminDetails adminDetails = new AdminDetails(new Admin());
        assertTrue(adminDetails.isEnabled());
    }
}
