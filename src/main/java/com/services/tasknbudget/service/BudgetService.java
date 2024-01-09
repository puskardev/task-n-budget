package com.services.tasknbudget.service;

import com.services.tasknbudget.entity.Budget;
import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	public Budget getBudgetByDate(final String budgetDate) {
		try {
			return budgetRepository.findByBudgetDate(budgetDate);
		} catch (Exception e) {
			throw new RuntimeException("Fetching Budget Failed: " + e.getMessage(), null);
		}
	}
}
