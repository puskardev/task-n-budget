package com.services.tasknbudget.dto;

import java.util.ArrayList;
import java.util.List;

public class BudgetDTO {
	private Integer id;
	private String budgetDate;
	private BudgetDetailsDTO budgetDetails;
	private List<ExpenseDTO> expenses = new ArrayList<>();
	private List<IncomeDTO> income = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(String budgetDate) {
		this.budgetDate = budgetDate;
	}

	public BudgetDetailsDTO getBudgetDetails() {
		return budgetDetails;
	}

	public void setBudgetDetails(BudgetDetailsDTO budgetDetails) {
		this.budgetDetails = budgetDetails;
	}

	public List<ExpenseDTO> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<ExpenseDTO> expenses) {
		this.expenses = expenses;
	}

	public List<IncomeDTO> getIncome() {
		return income;
	}

	public void setIncome(List<IncomeDTO> income) {
		this.income = income;
	}
}
