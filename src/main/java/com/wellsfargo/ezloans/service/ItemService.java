package com.wellsfargo.ezloans.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;
import com.wellsfargo.ezloans.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	public void saveItem(Item i) {
		itemRepo.save(i);
		return;
	}
	
	public List<Item> listAll(Optional<Boolean> issueStatus, Optional<Category> itemMake) {
		if (issueStatus.isEmpty() && itemMake.isEmpty())
			return itemRepo.findAll();
		else if (issueStatus.isPresent() && itemMake.isPresent())
			return itemRepo.findByIssueStatusAndItemCategory(issueStatus.get(), itemMake.get());
		else if (issueStatus.isPresent())
			return itemRepo.findByIssueStatus(issueStatus.get());
		else
			return itemRepo.findByItemCategory(itemMake.get());
	}
	
	public void updateItem(Item i) throws Exception {
		Optional<Item> item = itemRepo.findById(i.getItemId());
		if(item.isEmpty()) {
			throw new Exception("Invalid Item Id");
		}
		if(item.get().getIssueStatus()==true && i.getIssueStatus()==false) {
			throw new Exception("Item issued, can't change issue status directly. Need to return item.");
		}
		itemRepo.save(i);
		return;
	}
	
	public void deleteItem(Item i) throws Exception {
		Optional<Item> item = itemRepo.findById(i.getItemId());
		if(item.isEmpty()) {
			throw new Exception("Invalid Item Id");
		}
		itemRepo.deleteById(i.getItemId());
		return;
	}
	
	public Item findById(String id) throws Exception {
		Optional<Item> item = itemRepo.findById(id);
		if(item.isEmpty()) {
			throw new Exception("Invalid Item Id");
		}
		return item.get();
	}
}
