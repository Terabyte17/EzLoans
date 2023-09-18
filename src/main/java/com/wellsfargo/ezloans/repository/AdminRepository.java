package com.wellsfargo.ezloans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wellsfargo.ezloans.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	public Optional<Admin> findByUsername(String username);
	 
	@Query("SELECT a FROM Admin a WHERE a.username = :username")
	public Admin getAdminByUsername(@Param("username") String username);
}
