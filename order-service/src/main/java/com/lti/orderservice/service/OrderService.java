package com.lti.orderservice.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.lti.orderservice.dto.InventoryResponse;
import com.lti.orderservice.dto.OrderLineItemsDto;
import com.lti.orderservice.dto.OrderRequest;
import com.lti.orderservice.model.Order;
import com.lti.orderservice.model.OrderLineItems;
import com.lti.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	WebClient webClient;

	@Autowired(required = true)
	RestTemplate restTemplate;
	
	public void placeOrder(OrderRequest orderRequest) {

		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
				.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
		order.setOrderLineItems(orderLineItemsList);
		List<String> skuCodes = order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode())
				.toList();

		System.out.println(skuCodes);
		// System.out.println("uribuilder
		// -->"+uribuilder->uribuilder.queryParam("skuCode",skuCodes));

		/*
		 * InventoryResponse[] result= webClient.get()
		 * .uri("http://localhost:8082/api/inventory",
		 * uribuilder->uribuilder.queryParam("skuCode",skuCodes).build()) .retrieve()
		 * .bodyToMono(InventoryResponse[].class) .block();
		 */

		String url = "http://localhost:8082/api/inventory?skuCode={id}&skuCode={name}";
	    Map<String, String> params = Collections.singletonMap("id", "123");
	    params.put("name", "Alice");
	    InventoryResponse[] result = restTemplate.getForObject(url, InventoryResponse[].class, params);
		//InventoryResponse[] result = null;
		Arrays.stream(result).forEach(x -> System.out.println(x.getSkuCode() + "   " + x.isInStock()));

		boolean allProductsInStock = Arrays.stream(result).allMatch(inventoryresponse -> inventoryresponse.isInStock());

		if (allProductsInStock) {
			orderRepository.save(order);
		} else {

			throw new IllegalArgumentException("Product Is not in stock, please try again later");
		}

	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItems.getSkuCode());
		return orderLineItems;
	}

}
