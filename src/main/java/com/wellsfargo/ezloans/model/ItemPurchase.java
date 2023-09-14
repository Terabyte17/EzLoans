package com.wellsfargo.ezloans.model;

import java.util.Date;

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
	@JsonIgnoreProperties(value = {"employeeName", "designation", "department", "gender", "dob", "doj", "itemsPurchased", "loanCards"}, allowSetters=true)
	private Employee emp;
	
	@OneToOne
	@JoinColumn(name="itemId")
	@JsonIgnoreProperties(value = {"itemDesc", "issueStatus", "itemMake", "itemCategory", "itemPurchased"}, allowSetters=true)
	private Item item;
	
	@Column(nullable = false)
	private Date purchaseDate;

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
	
}
