package com.finalyearapp.agrifarming.service;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.model.User;

import java.util.List;



public interface ExpenseService {
	List<Expense>getAllExspenses();
	void save(Expense expense);
	Expense getExpenseById(Long id);
	void deleteById(Long id);

}
