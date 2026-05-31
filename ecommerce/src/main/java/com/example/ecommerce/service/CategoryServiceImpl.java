package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Category saveCategories(Category category) {
		// TODO Auto-generated method stub
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category No Found"));
	}

	@Override
	public Category updateCategory(Long id, Category category) {
		// TODO Auto-generated method stub
		Category existing = getCategoryById(id);
		existing.setName(category.getName());
		existing.setDescription(category.getDescription());
		return categoryRepo.save(existing);
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		Category category=getCategoryById(id);
		categoryRepo.delete(category);
	}

}
