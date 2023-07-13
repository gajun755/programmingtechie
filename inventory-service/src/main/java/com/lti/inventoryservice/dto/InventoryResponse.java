package com.lti.inventoryservice.dto;


public class InventoryResponse {

		private String skuCode;
		private boolean isInStock;
		
		public String getSkuCode() {
			return skuCode;
		}
		public void setSkuCode(String skuCode) {
			this.skuCode = skuCode;
		}
		public boolean isInStock() {
			return isInStock;
		}
		public void setInStock(boolean isInStock) {
			this.isInStock = isInStock;
		}
		@Override
		public String toString() {
			return "InventoryResponse [skuCode=" + skuCode + ", isInStock=" + isInStock + "]";
		}
		
		
		
		
}
