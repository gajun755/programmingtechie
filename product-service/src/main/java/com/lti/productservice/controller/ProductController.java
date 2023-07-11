package com.lti.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.productservice.dto.ProductRequest;
import com.lti.productservice.dto.ProductResponse;
import com.lti.productservice.service.ProdcutService;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	ProdcutService productService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> findAll() {

		return productService.findAllProducts();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {

		productService.createProduct(productRequest);
	}

}
