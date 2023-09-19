package com.wellsfargo.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.Category;
import com.wellsfargo.ezloans.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	List<Item> findByIssueStatus(Boolean issueStatus);
	List<Item> findByItemCategory(Category itemCategory);
	List<Item> findByIssueStatusAndItemCategory(Boolean issueStatus, Category ItemCategory);
}
