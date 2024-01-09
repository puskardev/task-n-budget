package com.services.tasknbudget.repository;

import com.services.tasknbudget.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    public Expense findByExpenseId(Integer expenseId);

    public Expense findByBudgetId(Integer budgetId);
}
