package com.finalyearapp.agrifarming.repository;

import com.finalyearapp.agrifarming.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ExpenseRepository extends JpaRepository<Expense,Long>{

}
