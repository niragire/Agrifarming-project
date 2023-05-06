package com.finalyearapp.agrifarming.service;

import java.util.List;
import java.util.Optional;

import com.finalyearapp.agrifarming.model.Expense;
import com.finalyearapp.agrifarming.model.User;
import com.finalyearapp.agrifarming.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class ExpenseImpl implements ExpenseService{
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private UserService userService;
	@Override
	public List<Expense> getAllExspenses() {
		// TODO Auto-generated method stub
		
		return expenseRepository.findAll();
	}

	@Override
	public void save(Expense expense) {
		// TODO Auto-generated method stub
		expenseRepository.save(expense);
		
	}
//	public List<Expense> findAllExpenses(Long user_id){
//		return expenseRepository.findAllById(user_id);
//	}
	public List<Expense>findAllExpenses(){
		return expenseRepository.findAll();
	}
	@Override
	public Expense getExpenseById(Long id) {
		// TODO Auto-generated method stub
		Optional<Expense> optional=expenseRepository.findById(id);
		Expense exp=null;
		if(optional.isPresent()) {
			exp=optional.get();
		}
		return exp;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		boolean exists=expenseRepository.existsById(id);
		if(!exists) {
			throw new RuntimeException("Expense With this id does not exist");
		}
		this.expenseRepository.deleteById(id);
		
	}

}
