package com.finalyearapp.agrifarming.controller;

import com.finalyearapp.agrifarming.model.Category;
import com.finalyearapp.agrifarming.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/homeCategory")
	public String homePage(Model model) {
		model.addAttribute("listAllCategories", categoryService.getAllCategories());
		return "category";
	}
	@GetMapping("/showNewCategory")
	public String showNewExpenses(Model model) {
		Category category=new Category();
		model.addAttribute("category", category);
		return "NewCategory";
	}
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		try {
			categoryService.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/api/category/homeCategory";
	}
	@GetMapping("/updateCategoryById/{id}")
	public String findCategoryByid(@PathVariable(value = "id") long id,Model model) {
		try {
		Category category=categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "NewCategory";
		
	}
	@GetMapping("/updateCategory")
	public String findUpdateCategory(Model model) {
		//try {
		// Category category=categoryService.findCategoryById(id);
		// model.addAttribute("category", category);
		// }catch(Exception e) {
		// 	e.printStackTrace();
		// }
		String category ="Ibirayi";
		model.addAttribute("categoryName", category);
		return "NewCategory";
	}
	@GetMapping("/deleteCategoryById/{id}")
	public String deleteCategoryById(@PathVariable(value = "id")long id,Model model) {
		try {
			categoryService.deleteCategoryByid(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/api/category/homeCategory";
	}
}
