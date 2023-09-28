package com.wellsfargo.ezloans.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.exception.InvalidRequestException;
import com.wellsfargo.ezloans.exception.ResourceNotFoundException;
import com.wellsfargo.ezloans.model.ItemPurchase;
import com.wellsfargo.ezloans.service.ItemPurchaseService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemPurchaseController {
	
	@Autowired
	private ItemPurchaseService ips;
	
	@ResponseBody
	@PostMapping("/purchaseItem")
	public ResponseEntity<String> purchaseItem(@Validated @RequestBody ItemPurchase ip) throws Exception {
		try {
			ips.itemPurchased(ip);
			return new ResponseEntity<>("Successfully purchased item.", HttpStatus.OK);
		}
		catch (ResourceNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (InvalidRequestException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while purchasing item.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Validated
	@GetMapping("/allItems/{id}")
	public ResponseEntity<?> getAllItems(@PathVariable String id) {
		try {
			Set<ItemPurchase> itemsPurchased = ips.viewAllItemsPurchased(id);
			if (itemsPurchased.isEmpty())
				return new ResponseEntity<>("No items purchased till now.", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(itemsPurchased, HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while fetching list of purchased items.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
