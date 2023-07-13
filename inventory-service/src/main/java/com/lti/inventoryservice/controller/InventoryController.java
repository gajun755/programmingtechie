package com.lti.inventoryservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.inventoryservice.dto.InventoryResponse;
import com.lti.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam String skuCode) {
	
		List<String> list=new ArrayList<>();
			if(skuCode.startsWith("[")) {
		String result= skuCode.substring(1,skuCode.length()-1);
		String[] strArr=result.split(",");
		
			for(String s:strArr) {
			list.add(s.trim());
		}
		
		 return inventoryService.isInStock(list);
		 
			}
			else {
			for(String s:skuCode.split(",")) {
				list.add(s);
			}
		
				return inventoryService.isInStock(list);
			}
				//return null;
	}
}
