package com.wellsfargo.ezloans.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// need to make these human readable either here or in js frontend



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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Boolean getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Boolean issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getItemMake() {
		return itemMake;
	}

	public void setItemMake(String itemMake) {
		this.itemMake = itemMake;
	}

	public Category getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(Category itemCategory) {
		this.itemCategory = itemCategory;
	}

	public ItemPurchase getItemPurchased() {
		return itemPurchased;
	}

	public void setItemPurchased(ItemPurchase itemPurchased) {
		this.itemPurchased = itemPurchased;
	}
	
	
	
}
