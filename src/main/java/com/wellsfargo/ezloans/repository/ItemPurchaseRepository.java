package com.wellsfargo.ezloans.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.ItemPurchase;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, Long> {
	
	// Property Expressions
	public Set<ItemPurchase> findByEmpEmployeeId(String eid);
	
	public ItemPurchase findByItemItemId(String itemId);
}
