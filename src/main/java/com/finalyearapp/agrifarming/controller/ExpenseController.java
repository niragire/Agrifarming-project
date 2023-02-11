package com.finalyearapp.agrifarming.controller;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.model.User;
import com.finalyearapp.agrifarming.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/expense")
public class ExpenseController{
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExpenseImpl expense;
	@GetMapping("/homeExpenses")
	public String homePage(Model model,Authentication authentication) {
		String findAuthenticatedUser=authentication.getName();
		User user=userService.findByEmail(findAuthenticatedUser);
		model.addAttribute("listAllExpenses", expense.findAllExpenses(user.getId()));
		return "expense";
	}
	@GetMapping("/showNewExpenses")
	public String showNewExpenses(Model model) {
		Expense expense=new Expense();
		model.addAttribute("expense", expense);
		model.addAttribute("listAllCategories", categoryService.getAllCategories());
		return "NewExpenses";
	}
	@PostMapping("/saveExpense")
	public String saveExpenses(@ModelAttribute("expense") Expense expense, Model model, Authentication authentication) {
		try {
			String findAuthenticatedUser=authentication.getName();
			User user=userService.findByEmail(findAuthenticatedUser);
			System.out.println(user.getId());
			expense.setUser(user);
			expenseService.save(expense);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/api/expense/homeExpenses";
	}
	@GetMapping("/updateExpenseById/{id}")
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
	@GetMapping("/deleteExpenseById/{id}")
	public String deleteExpenseById(@PathVariable(value = "id")long id,Model model) {
		try {
			expenseService.deleteById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/api/expense/homeExpenses";
	}
}
