package com.wellsfargo.ezloans.model;

import java.util.Date;

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
	private Employee emp;
	
	@OneToOne
	@JoinColumn(name="itemId")
	private Item item;
	
	@Column(nullable = false)
	private Date purchaseDate;
	
}
