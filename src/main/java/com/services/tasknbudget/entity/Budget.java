package com.services.tasknbudget.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer budgetId;

	@OneToOne(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private BudgetDetails budgetDetails;

	@OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Expense> expenses = new ArrayList<>();

	@OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "incomeId", referencedColumnName = "incomeId")
	private List<Income> income = new ArrayList<>();

	private String budgetDate;

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Income> getIncome() {
		return income;
	}

	public void setIncome(List<Income> income) {
		this.income = income;
	}

	public String getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(String budgetDate) {
		this.budgetDate = budgetDate;
	}

	public BudgetDetails getBudgetDetails() {
		return budgetDetails;
	}

	public void setBudgetDetails(BudgetDetails budgetDetails) {
		this.budgetDetails = budgetDetails;
	}
}
