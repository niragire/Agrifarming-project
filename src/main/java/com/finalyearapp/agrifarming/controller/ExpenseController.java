package com.finalyearapp.agrifarming.controller;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.service.CategoryService;
import com.finalyearapp.agrifarming.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExpenseController{
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("/api/homeExpenses")
	public String homePage(Model model) {
		model.addAttribute("listAllExpenses", expenseService.getAllExspenses());
		return "expense";
	}
	@GetMapping("/api/showNewExpenses")
	public String showNewExpenses(Model model) {
		Expense expense=new Expense();
		model.addAttribute("expense", expense);
		model.addAttribute("listAllCategories", categoryService.getAllCategories());
		return "NewExpenses";
	}
	@PostMapping("/api/saveExpense")
	public String saveExpenses(@ModelAttribute("expense") Expense expense) {
		try {
			expenseService.save(expense);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/api/homeExpenses";
	}
	@GetMapping("/api/updateExpenseById/{id}")
	public String findExpenseByid(@PathVariable(value = "id") long id,Model model) {
		try {
		Expense expense=expenseService.getExpenseById(id);
		model.addAttribute("expense", expense);
		model.addAttribute("listAllCategories", categoryService.getAllCategories());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "updateExpense";
	}
	@GetMapping("/api/deleteExpenseById/{id}")
	public String deleteExpenseById(@PathVariable(value = "id")long id,Model model) {
		try {
			expenseService.deleteById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/api/homeExpenses";
	}
}
