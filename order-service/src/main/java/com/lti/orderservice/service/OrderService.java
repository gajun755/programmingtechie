package com.lti.orderservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.lti.orderservice.dto.InventoryResponse;
import com.lti.orderservice.dto.OrderLineItemsDto;
import com.lti.orderservice.dto.OrderRequest;
import com.lti.orderservice.exeption.ProductNotFound;
import com.lti.orderservice.model.Order;
import com.lti.orderservice.model.OrderLineItems;
import com.lti.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	/*
	 * @Autowired WebClient webClient;
	 */
	
	@Autowired
	RestTemplate restTemplate;
	
	/*
	 * public OrderService(RestTemplateBuilder restTemplateBuilder) {
	 * 
	 * this.restTemplate=restTemplateBuilder.build(); }
	 */
	
	
	public void placeOrder(OrderRequest orderRequest) throws ProductNotFound {

		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
		order.setOrderLineItems(orderLineItemsList);
		List<String> skuCodes = order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode())
				.toList();

		String url = "http://inventory-service/api/inventory?skuCode={id}";
	    Map<String, List<String>> params = new HashMap<>(); 
	    		params.put("id", skuCodes);
	    InventoryResponse[] result = restTemplate.getForObject(url, InventoryResponse[].class, params);
		//InventoryResponse[] result = null;
		Arrays.stream(result).forEach(x -> System.out.println(x.getSkuCode() + "   " + x.isInStock()));

		boolean allProductsInStock = Arrays.stream(result).allMatch(inventoryresponse -> inventoryresponse.isInStock());

		if (allProductsInStock) {
			orderRepository.save(order);
		} else {

			throw new ProductNotFound("Product Is not in stock, please try again later");
		}

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}

}
