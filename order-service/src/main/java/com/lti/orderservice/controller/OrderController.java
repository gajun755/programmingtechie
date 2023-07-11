package com.lti.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.orderservice.dto.OrderRequest;
import com.lti.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	
		@Autowired
		OrderService orderService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		
		System.out.println("OrderRequest"+orderRequest.toString());
		orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
	}
}
