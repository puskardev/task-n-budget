package com.services.tasknbudget.entity;

import jakarta.persistence.*;

@Entity
public class BudgetDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetDetailsId;

    @OneToOne(fetch = FetchType.LAZY)
    private Budget budget;

    private Double totalIncome;
    private Double totalExpenses;
    private Double totalMiscExpenses;
    private Double totalCreditCardExpenses;
    private Double totalSubscriptionExpenses;
    private Double totalSavings;


    public Integer getBudgetDetailsId() {
        return budgetDetailsId;
    }

    public void setBudgetDetailsId(Integer budgetDetailsId) {
        this.budgetDetailsId = budgetDetailsId;
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
