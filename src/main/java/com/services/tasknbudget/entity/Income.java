package com.services.tasknbudget.entity;

import jakarta.persistence.*;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Budget budget;

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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
