package com.wellsfargo.ezloans.repository;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wellsfargo.ezloans.model.Admin;
 
public class AdminDetails implements UserDetails {
 
    private Admin admin;
     
    public AdminDetails(Admin admin) {
        this.admin = admin;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(admin.getRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return admin.getPassword();
    }
 
    @Override
    public String getUsername() {
        return admin.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}
