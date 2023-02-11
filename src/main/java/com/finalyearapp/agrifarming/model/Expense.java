package com.finalyearapp.agrifarming.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseId;
	private String nameOfExpense;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category category;
	private String supplier;
	private int quantity;
	private int amountSpent;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	public Long getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}
	
	public String getNameOfExpense() {
		return nameOfExpense;
	}
	public void setNameOfExpense(String nameOfExpense) {
		this.nameOfExpense = nameOfExpense;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(int amountSpent) {
		this.amountSpent = amountSpent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
