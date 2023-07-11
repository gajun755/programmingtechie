package com.lti.orderservice.dto;

import java.math.BigDecimal;

public class OrderLineItemsDto {

	private String orderNumber;

	private String skuCode;

	private BigDecimal price;

	private Integer quantity;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderLineItemsDto [orderNumber=" + orderNumber + ", skuCode=" + skuCode + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

	
}
