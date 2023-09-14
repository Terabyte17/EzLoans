package com.wellsfargo.ezloans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.service.ItemService;


@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/items")
	public Item saveItem(@Validated @RequestBody Item item) {
		Item itm = itemService.saveItem(item);
		return itm;
	}
	
	@Validated
	@GetMapping("/items")
	public List<Item> getAllEmployee() {
		return itemService.listAll();
	}

}
