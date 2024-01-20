package com.services.tasknbudget.repository;

import com.services.tasknbudget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Optional<Budget> findByBudgetDate(String budgetDate);
//    public Optional<List<Expense>> findExpensesById(Integer id);
//
//    public Optional<List<Income>> findIncomeById(Integer id);
//
//    public Optional<BudgetDetails> findBudgetDetailsById(Integer id);
}
