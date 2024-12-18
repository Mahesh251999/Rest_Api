package org.jsp.rest_api.controller;

import java.util.List;

import org.jsp.rest_api.dto.Product;
import org.jsp.rest_api.service.Product_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Product_controller {
  
	@Autowired
	Product_service service;
	
	
	//save one product
	@PostMapping("/products")
	public ResponseEntity<Object> saveProducts(@RequestBody Product product){
		return service.saveProduct(product);
		
	}
	
	//save multiple products
	@PostMapping("/products/many")
	public ResponseEntity<Object> saveProducts(@RequestBody List<Product> products){
		return service.saveProducts(products);
	}
	
	//Fetch All Products
	@GetMapping("/products")
	public ResponseEntity<Object> fetchAllProducts(){
		return service.fetchAllProducts();
	}
	
	//Fetch Product By Id
		@GetMapping("/products/{id}")
		public ResponseEntity<Object> fetchById(@PathVariable int id){
			return service.fetchById(id);
		}
		
		//Fetch Product By Name
		@GetMapping("/products/name/{name}")
		public ResponseEntity<Object> fetchByName(@PathVariable String name){
			return service.fetchByName(name);
		}
		
		//Fetch Products Price Greater Than
		@GetMapping("/products/price/greater/{price}")
		public ResponseEntity<Object> fetchByPriceGreater(@PathVariable double price){
			return service.fetchByPriceGreater(price);
		}
		
		//Fetch Products By Stock Between
		@GetMapping("/products/stock/{min}/{max}")
		public ResponseEntity<Object> fetchByStockBetween(@PathVariable int min,@PathVariable int max){
			return service.fetchByStockBetween(min,max);
		}
		
		//Delete Product By Id
		@DeleteMapping("/products/{id}")
		public ResponseEntity<Object> deleteById(@PathVariable int id){
			return service.deleteById(id);
		}
		
		//Update Product -PUT
		@PutMapping("/products")
		public ResponseEntity<Object> updateRecord(@RequestBody Product product){
			return service.updateProduct(product);
		}
		
		//Update Product -PATCH
		@PutMapping("/products/{id}")
		public ResponseEntity<Object> updateRecord(@PathVariable int id,@RequestBody Product product){
			return service.updateProduct(id,product);
}
		
}
