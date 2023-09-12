package com.wellsfargo.ezloans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findByEmail(String email);
	
}
