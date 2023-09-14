package com.wellsfargo.ezloans.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.service.ItemPurchaseService;

@RestController
@RequestMapping("/api")
public class ItemPurchaseController {
	
	@Autowired
	private ItemPurchaseService ips;
	
	@PostMapping("/purchaseItem")
	public String purchaseItem(@Validated @RequestBody ItemPurchase ip) throws Exception {
		ItemPurchase purchaseReceipt = ips.itemPurchased(ip);
		return "done";
	}
	
	@Validated
	@GetMapping("/allItems/{id}")
	public Set<ItemPurchase> getAllItems(@PathVariable String id) {
		return ips.viewAllItemsPurchased(id);
	}
}
