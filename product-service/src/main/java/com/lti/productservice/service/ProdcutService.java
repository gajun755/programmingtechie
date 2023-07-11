package com.lti.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.productservice.dto.ProductRequest;
import com.lti.productservice.dto.ProductResponse;
import com.lti.productservice.model.Product;
import com.lti.productservice.repository.ProductRepository;

@Service
public class ProdcutService {
	
		//Logger logger=Logger.ge;
	
		@Autowired
		ProductRepository productRepository;
	
		public void createProduct(ProductRequest productRequest) {
			Product product=new Product();
			product.setName(productRequest.getName());
			product.setDescription(productRequest.getDescription());
			product.setPrice(productRequest.getPrice());
		
			productRepository.save(product);
			//logger.info("Product {} is saved",product.getName());
				
		}

		public List<ProductResponse> findAllProducts() {
			
			List<Product> products= productRepository.findAll();
			return products.stream().map(product-> mapToProductResponse(product)).toList();
		}
		
		 private ProductResponse mapToProductResponse(Product product){
			
			 	ProductResponse productResponse=new ProductResponse();
			 		productResponse.setName(product.getName());
			 		productResponse.setDescription(product.getDescription());
			 		productResponse.setPrice(product.getPrice());
			 		return productResponse;
		 }
		
}
