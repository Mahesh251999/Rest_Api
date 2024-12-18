package org.jsp.rest_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsp.rest_api.dto.Product;
import org.jsp.rest_api.repository.Product_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Product_service {

	@Autowired
	Product_repository repository;

	public ResponseEntity<Object> saveProduct(Product product) {
		repository.save(product);
		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("messege", "Products Added Success");
		map.put("data", product);
		return  new ResponseEntity<Object>(map,HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> saveProducts(List<Product>products){
		repository.saveAll(products);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("messege", "Products Added Success");
		map.put("data", products);
		return new ResponseEntity<Object>(map,HttpStatus.CREATED);
		
	}

	public ResponseEntity<Object> fetchAllProducts() {
		List<Product> list = repository.findAll();
		if (list.isEmpty()) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("error", "No produts Found");
			
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}else {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("messege", "produts Found");
			
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
	}
	public ResponseEntity<Object> fetchById(int id) {
		Optional<Product> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found with Id: "+id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", optional.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}
	public ResponseEntity<Object> fetchByName(String name) {
		List<Product> list = repository.findByName(name);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found with Name :"+name);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}
	public ResponseEntity<Object> fetchByPriceGreater(double price) {
		List<Product> list = repository.findByPriceGreaterThanEqual(price);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found Price Greater Than: "+price);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}
	public ResponseEntity<Object> fetchByStockBetween(int min, int max) {
		List<Product> list = repository.findByStockBetween(min,max);
		if (list.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found Stock Between: "+min+" and "+max);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Products Found");
			map.put("data", list);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> deleteById(int id) {
		Optional<Product> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Products Found with Id: "+id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			repository.deleteById(id);
			map.put("message", "Products Delete Success");
			
			return new ResponseEntity<Object>(map,HttpStatus.OK);
	}
}

	public ResponseEntity<Object> updateProduct(Product product) {
		repository.save(product);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Product Updated Success");
		
		return new ResponseEntity<Object>(map,HttpStatus.OK);
	}

	public ResponseEntity<Object> updateProduct(int id, Product product) {
		Optional<Product> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error","No Product Found with Id: "+id);
			
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Product existingProduct = optional.get();
			if(product.getName()!=null)
				existingProduct.setName(product.getName());
			if(product.getDescription()!=null)
				existingProduct.setDescription(product.getDescription());
			if(product.getPrice()!=0)
				existingProduct.setPrice(product.getPrice());
			if(product.getStock()!=0)
				existingProduct.setStock(product.getStock());
			
			repository.save(existingProduct);
			
			map.put("message", "Product Updated Success");
			
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		
		}
		
	}
}
