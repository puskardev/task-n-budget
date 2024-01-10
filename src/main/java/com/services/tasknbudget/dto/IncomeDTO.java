package com.services.tasknbudget.dto;

public class IncomeDTO {
	private Integer incomeId;
	private Integer budgetId;
	private String source;
	private Double amount;
	private String incomeCategoryType;
	private String depositDate;
	private String depositTo;
	private String note;

	public Integer getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(Integer incomeId) {
		this.incomeId = incomeId;
	}

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIncomeCategoryType() {
		return incomeCategoryType;
	}

	public void setIncomeCategoryType(String incomeCategoryType) {
		this.incomeCategoryType = incomeCategoryType;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	public String getDepositTo() {
		return depositTo;
	}

	public void setDepositTo(String depositTo) {
		this.depositTo = depositTo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
