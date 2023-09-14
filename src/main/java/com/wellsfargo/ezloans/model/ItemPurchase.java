package com.wellsfargo.ezloans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ItemPurchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long issueId;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	@JsonIgnoreProperties(value = {"employeeName", "designation", "department", "gender", "dob", "doj", "itemsPurchased"}, allowSetters=true)
	private Employee emp;
	
	@OneToOne
	@JoinColumn(name="itemId")
	@JsonIgnoreProperties(value = {"itemDesc", "issueStatus", "itemMake", "itemCategory", "itemPurchased"}, allowSetters=true)
	private Item item;
	
	@Column(nullable = false)
	private Date purchaseDate;
	
}
