package com.wellsfargo.ezloans.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.model.Message;
import com.wellsfargo.ezloans.service.ItemService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/items")
	public Message saveItem(@Validated @RequestBody Item item) {
		// if the same id already exists?
		itemService.saveItem(item);
		return new Message("Item saved successfully.");
	}
	
	@Validated
	@GetMapping("/items")
	public List<Item> getAllEmployee(@RequestParam Optional<Boolean> issue_status, @RequestParam Optional<Category> item_category) {
		return itemService.listAll(issue_status, item_category);
	}
	
	@PostMapping("/items/update")
	@ResponseBody
	public Message updateItem(@Validated @RequestBody Item item) {
		try {
			itemService.updateItem(item);
			return new Message("Item updated successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Item ID. Updation failed.");
		}
	}
	
	@PostMapping("/items/delete")
	@ResponseBody
	public Message deleteItem(@Validated @RequestBody Item item) {
		try {
			itemService.deleteItem(item);
			return new Message("Item deleted successfully.");
		}
		catch (Exception ex) {
			return new Message("Invalid Item ID. Deletion failed.");
		}
	}

}
