package com.wellsfargo.ezloans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	public Item saveItem(Item i) {
		return itemRepo.save(i);
	}
	
	public List<Item> listAll() {
		return itemRepo.findAll();
	}
}
