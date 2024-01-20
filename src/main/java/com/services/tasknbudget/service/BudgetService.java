package com.services.tasknbudget.service;

import com.services.tasknbudget.dto.BudgetDTO;
import com.services.tasknbudget.entity.Budget;
import com.services.tasknbudget.mapper.ExpenseMapper;
import com.services.tasknbudget.mapper.IncomeMapper;
import com.services.tasknbudget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private ExpenseMapper expenseMapper;

	@Autowired
	private IncomeMapper incomeMapper;

	@Autowired
	private BudgetDetailsService budgetDetailsService;

	public BudgetDTO getBudgetByDate(final String budgetDate) {
		try {
			Budget entity = budgetRepository.findByBudgetDate(budgetDate)
					.orElseGet(() -> {
						return createBudgetByDate(budgetDate);
					});

			BudgetDTO budgetDTO = new BudgetDTO();
			budgetDTO.setId(entity.getId());
			budgetDTO.setBudgetDate(entity.getBudgetDate());

			entity.getExpenses().stream()
					.map(expenseMapper::toDto)
					.forEach(budgetDTO.getExpenses()::add);

			entity.getIncome().stream()
					.map(incomeMapper::toDto)
					.forEach(budgetDTO.getIncome()::add);

			budgetDTO.setBudgetDetails(
					budgetDetailsService.getBudgetDetails(entity.getId(), budgetDTO.getExpenses(), budgetDTO.getIncome()));

			return budgetDTO;
		} catch (Exception e) {
			throw new RuntimeException("Fetching Budget Failed: " + e.getMessage(), null);
		}
	}

	public BudgetDTO createBudget(final String date) {
		try {
			Budget entity = createBudgetByDate(date);
			BudgetDTO budgetDTO = new BudgetDTO();
			budgetDTO.setId(entity.getId());
			budgetDTO.setBudgetDate(entity.getBudgetDate());

			return budgetDTO;
		} catch (Exception e) {
			throw new RuntimeException("Error creating budget: " + e.getMessage(), null);
		}
	}

	public Budget createBudgetByDate(final String budgetDate) {
		Budget budget = new Budget();
		budget.setBudgetDate(budgetDate);
		return budgetRepository.save(budget);
	}
}
