package com.wellsfargo.ezloans.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.service.ItemService;


@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/items")
	public ResponseEntity<String> saveItem(@Validated @RequestBody Item item) {
		// if the same id already exists?
		try {
			itemService.saveItem(item);
			return new ResponseEntity<>("Item saved successfully.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while saving item.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Validated
	@GetMapping("/items")
	public ResponseEntity<?> getAllItems(@RequestParam Optional<Boolean> issue_status, @RequestParam Optional<Category> item_category) {
		
		try {
			List<Item> items = itemService.listAll(issue_status, item_category);
			if (items.isEmpty())
				return new ResponseEntity<>("No such item found.", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(items, HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Error while fetching items.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/items/update")
	@ResponseBody
	public ResponseEntity<String> updateItem(@Validated @RequestBody Item item) {
		try {
			itemService.updateItem(item);
			return new ResponseEntity<>("Item updated successfully.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Invalid Item ID. Updation failed.", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/items/delete")
	@ResponseBody
	public ResponseEntity<String> deleteItem(@Validated @RequestBody Item item) {
		try {
			itemService.deleteItem(item);
			return new ResponseEntity<>("Item deleted successfully.", HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>("Invalid Item ID. Deletion failed.", HttpStatus.NOT_FOUND);
		}
	}

}
