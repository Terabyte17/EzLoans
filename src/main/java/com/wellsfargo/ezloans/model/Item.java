package com.wellsfargo.ezloans.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// need to make these human readable either here or in js frontend

enum Category {
	Furniture,
	SportsEquipments,
	ElectronicGadgets
};

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
	
	@Id
	@UuidGenerator
	private String itemId;
	
	private String itemDesc;
	
	@Column(nullable = false)
	private Boolean issueStatus;
	
	@Column(nullable = false)
	private String itemMake;
	
	@Column(nullable = false)
	private Category itemCategory;
	
	@OneToOne(mappedBy="item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ItemPurchase itemPurchased;
	
}
