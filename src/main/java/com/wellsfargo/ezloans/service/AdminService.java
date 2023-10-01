package com.wellsfargo.ezloans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Admin;
import com.wellsfargo.ezloans.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	public Admin registerAdmin(Admin a) {
		return adminRepo.save(a);
	}
	
	public Optional<Admin> loginAdmin(String username) {
		return adminRepo.findByUsername(username);
	}
	
}
