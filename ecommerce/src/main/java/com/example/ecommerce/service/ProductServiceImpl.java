package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product saveAllProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		// TODO Auto-generated method stub
		Product existing = getProductById(id);
		existing.setName(product.getName());
		existing.setDescription(product.getDescription());
		existing.setPrice(product.getPrice());
		existing.setStockQuantity(product.getStockQuantity());
		return productRepo.save(existing);
	}

	@Override
	public List<Product> searchProduct(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByNameContainingIgnoreCase(name);
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
	}

}
