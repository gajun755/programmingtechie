package com.lti.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.productservice.dto.ProductRequest;
import com.lti.productservice.repository.ProductRepository;

import junit.framework.Assert;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	ProductRepository productRepository;
	
	@Container
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");
	
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		
	}
	
	
	
	@Test
	void shouldCreateProduct() throws JsonProcessingException, Exception {
		
		//ProductRequest a= getProductreuest();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getProductreuest()))).andExpect(status().isCreated());
		Assert.assertTrue(productRepository.findAll().size()==2);
	}



	private ProductRequest getProductreuest() {
		
		ProductRequest productRequest=new ProductRequest();
		productRequest.setName("IPHONe");
		productRequest.setPrice(BigDecimal.valueOf(1200));
		productRequest.setDescription("iphone 13");
		return productRequest;
	}
	
	

}
