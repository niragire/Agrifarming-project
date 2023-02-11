package com.finalyearapp.agrifarming.service;

import com.finalyearapp.agrifarming.model.Category;

import java.util.List;


public interface CategoryService {
	List<Category>getAllCategories();
	void saveCategory(Category category);
	Category findCategoryById(Long id);
	void deleteCategoryByid(Long id);
}
