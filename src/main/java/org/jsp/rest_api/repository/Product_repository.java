package org.jsp.rest_api.repository;

import java.util.List;

import org.jsp.rest_api.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_repository extends JpaRepository<Product, Integer> {

	
	List<Product> findByName(String name);
	List<Product> findByPriceGreaterThanEqual(double price);
	List<Product> findByStockBetween(int min, int max);
}
