package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.model.Product;

public interface ProductService {
	Product saveAllProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(Long id);
	Product updateProduct(Long id, Product product);
	Product searchProduct(String name);
	void deleteProduct(Long id);
}
