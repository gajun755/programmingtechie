package com.lti.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.inventoryservice.dto.InventoryResponse;
import com.lti.inventoryservice.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
