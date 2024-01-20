package com.services.tasknbudget.dto;

public class BudgetDetailsDTO {
	private Integer budgetId;
	private Double totalIncome;
	private Double totalExpenses;
	private Double totalMiscExpenses;
	private Double totalCreditCardExpenses;
	private Double totalSubscriptionExpenses;
	private Double totalSavings;

	public BudgetDetailsDTO(Integer budgetId) {
		this.budgetId = budgetId;
		this.totalIncome = 0.0;
		this.totalExpenses = 0.0;
		this.totalMiscExpenses = 0.0;
		this.totalCreditCardExpenses = 0.0;
		this.totalSubscriptionExpenses = 0.0;
		this.totalSavings = 0.0;
	}

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Double getTotalMiscExpenses() {
		return totalMiscExpenses;
	}

	public void setTotalMiscExpenses(Double totalMiscExpenses) {
		this.totalMiscExpenses = totalMiscExpenses;
	}

	public Double getTotalCreditCardExpenses() {
		return totalCreditCardExpenses;
	}

	public void setTotalCreditCardExpenses(Double totalCreditCardExpenses) {
		this.totalCreditCardExpenses = totalCreditCardExpenses;
	}

	public Double getTotalSubscriptionExpenses() {
		return totalSubscriptionExpenses;
	}

	public void setTotalSubscriptionExpenses(Double totalSubscriptionExpenses) {
		this.totalSubscriptionExpenses = totalSubscriptionExpenses;
	}

	public Double getTotalSavings() {
		return totalSavings;
	}

	public void setTotalSavings(Double totalSavings) {
		this.totalSavings = totalSavings;
	}
}
