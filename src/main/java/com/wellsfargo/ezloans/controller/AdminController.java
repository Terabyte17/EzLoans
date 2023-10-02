package com.wellsfargo.ezloans.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.Admin;
import com.wellsfargo.ezloans.service.AdminService;

@RestController
@RequestMapping(value = "/api")
public class AdminController {
	
	@Autowired
	private AdminService admin_service;
	
	public ResponseEntity<String> createAdmin(@Validated @RequestBody Admin a) {
		try {
			Admin registeredAdmin = admin_service.registerAdmin(a);
			if(registeredAdmin!=null) {
				return ResponseEntity.ok("Registration Successful!");
			}
			else {
				return ResponseEntity.badRequest().body("Registration Failed!");
			}
		}	
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
		}
	}
	
	@PostMapping("/login/admin")
	public String loginAdmin(@Validated @RequestBody Map<String, Object> payload) throws ResourceNotFoundException {
		try {
			String username = (String) payload.get("username");
			Optional<Admin> admin = admin_service.loginAdmin(username);
			if (admin.isEmpty())
				System.out.println("empty");
			if (admin.get().getRole().compareTo("ROLE_ADMIN")!=0)
				return null;			
			return "" + admin.get().getId();
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	
}
