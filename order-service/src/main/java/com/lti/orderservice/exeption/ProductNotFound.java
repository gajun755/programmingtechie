package com.lti.orderservice.exeption;

public class ProductNotFound extends Exception{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ProductNotFound(String message) {
			super(message);
		}
}
