package com.wellsfargo.ezloans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.ezloans.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
