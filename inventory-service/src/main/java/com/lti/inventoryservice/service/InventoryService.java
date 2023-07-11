package com.lti.inventoryservice.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.inventoryservice.dto.InventoryResponse;
import com.lti.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		
		
		System.out.println("inside the inventory service"+skuCode);
		inventoryRepository.findBySkuCodeIn(skuCode).stream().forEach(x->System.out.println(x));
		 return inventoryRepository.findBySkuCodeIn(skuCode)
				 .stream().map(inventory-> {InventoryResponse inventoryResponse=new InventoryResponse();
		 			inventoryResponse.setSkuCode(inventory.getSkuCode());
		 			inventoryResponse.setInStock(inventory.getQuantity()>0);
		 			return inventoryResponse; 			
		 }).toList();
		 
		 //list.stream().forEach(x->System.out.println(x));
		 

		 
	}

}
