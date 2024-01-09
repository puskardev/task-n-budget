package com.services.tasknbudget.service;

import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.repository.ExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	public Expense createExpense(final Expense expense) {
		try {
			return expenseRepository.save(expense);
		} catch (Exception e) {
			throw new RuntimeException("Error creating expense: " + e.getMessage(), null);
		}
	}

	public Optional<Expense> updateExpense(final Integer expenseId, final Expense newExpense) {
		try {
			return expenseRepository.findById(expenseId)
					.map(expense -> {
						BeanUtils.copyProperties(expense, newExpense);
						expense.setExpenseId(expenseId);
						return expenseRepository.save(expense);
					});
		} catch (Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage(), null);
		}
	}

	public Optional<Expense> getExpenseById(final Integer expenseId) {
		try {
			return expenseRepository.findById(expenseId);
		} catch (Exception e) {
			throw new RuntimeException("Expense Not Found: " + e.getMessage(), null);
		}
	}

	public void deleteExpenseById(final Integer expenseId) {
		try {
			expenseRepository.deleteById(expenseId);
		} catch (Exception e) {
			throw new RuntimeException("Delete Expense Failed: " + e.getMessage(), null);
		}
	}

	public List<Expense> getAllExpense() {
		try {
			return expenseRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Error getting all expenses: " + e.getMessage(), null);
		}
	}

}
