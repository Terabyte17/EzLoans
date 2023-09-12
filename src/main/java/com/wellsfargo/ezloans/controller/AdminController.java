package com.wellsfargo.ezloans.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import org.hibernate.mapping.List;
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
		
		String email = a.getEmail();
		String password = a.getPassword();
		
		Admin admin = admin_service.loginAdmin(email).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials."));
		
		System.out.println(password);
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = admin.getPassword();
        String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
        String admin_password = encodedString;
		System.out.println(admin.getPassword());
		System.out.println(admin_password);
		
		if(email.equals(admin.getEmail()) && password.equals(admin_password)) {
			isLoggedIn = true;
		}
		
		return isLoggedIn;
	}
}
