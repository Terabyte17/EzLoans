package com.wellsfargo.ezloans.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.wellsfargo.ezloans.model.Admin;
import com.wellsfargo.ezloans.repository.AdminDetails;
import com.wellsfargo.ezloans.repository.AdminRepository;
 
@Component
public class AdminDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AdminRepository adminRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Admin admin = adminRepository.getAdminByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Could not find user");
        }         
        return new AdminDetails(admin);
    }
 
}
