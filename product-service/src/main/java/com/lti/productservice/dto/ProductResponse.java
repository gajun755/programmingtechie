package com.lti.productservice.dto;

import java.math.BigDecimal;

public class ProductResponse {

		
		private String name;
		private String description;
		private BigDecimal price;
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		
		
	
}
