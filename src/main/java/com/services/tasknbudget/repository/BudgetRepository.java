package com.services.tasknbudget.repository;

import com.services.tasknbudget.entity.Budget;
import com.services.tasknbudget.entity.BudgetDetails;
import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Budget findByBudgetId(Integer budgetId);

    public List<Expense> findExpensesByBudgetId(Integer budgetId);

    public Budget findByBudgetDate(String budgetDate);

    public List<Income> findIncomeByBudgetId(Integer budgetId);

    public BudgetDetails findBudgetDetailsByBudgetId(Integer budgetId);
}
