package com.wellsfargo.ezloans.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String email;
	
	private String password;

	public Admin() {
		super();
	}
	
	public Admin(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		Base64.Encoder encoder = Base64.getEncoder();  
        String normalString = password;
        String encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
        this.password = encodedString;
	}
	
	
	
}
