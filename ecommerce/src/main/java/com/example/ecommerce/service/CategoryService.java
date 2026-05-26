package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.model.Category;

public interface CategoryService {
	Category saveCategories(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	Category updateCategory(Long id, Category category);
	void deleteCategory(Long id);
}
