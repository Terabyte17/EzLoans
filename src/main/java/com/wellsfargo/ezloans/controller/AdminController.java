package com.wellsfargo.ezloans.controller;

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
	
	@PostMapping("/login")
	public Boolean loginAdmin(@Validated @RequestBody Admin a) throws ResourceNotFoundException {
		
		Boolean isLoggedIn = false;
		
		String username = a.getUsername();
		String password = a.getPassword();
		
		Admin admin = admin_service.loginAdmin(username).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials."));

		
		if(username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
			isLoggedIn = true;
		}
		
		return isLoggedIn;
	}
}
