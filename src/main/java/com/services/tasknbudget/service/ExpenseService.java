package com.services.tasknbudget.service;

import com.services.tasknbudget.dto.ExpenseDTO;
import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.mapper.ExpenseMapper;
import com.services.tasknbudget.repository.ExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ExpenseMapper expenseMapper;

	public ExpenseDTO createExpense(final ExpenseDTO expense) {
		try {
			Expense entity = expenseMapper.toEntity(expense);
			return expenseMapper.toDto(expenseRepository.save(entity));
		} catch (Exception e) {
			throw new RuntimeException("Error creating expense: " + e.getMessage(), null);
		}
	}

	public Optional<ExpenseDTO> updateExpense(final Integer expenseId, final ExpenseDTO newExpense) {
		try {
			return expenseRepository.findById(expenseId)
					.map(expense -> {
						// Use MapStruct to map properties from DTO to the existing entity
						Expense updatedExpense = expenseMapper.toEntity(newExpense);
						updatedExpense.setExpenseId(expenseId);
						Expense savedExpense = expenseRepository.save(updatedExpense);
						return expenseMapper.toDto(savedExpense);
					});
		} catch (Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage(), null);
		}
	}

	public Optional<ExpenseDTO> getExpenseById(final Integer expenseId) {
		try {
			return expenseRepository.findById(expenseId)
					.map(expenseMapper::toDto);
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

	public List<ExpenseDTO> getAllExpense() {
		try {
			return expenseRepository.findAll()
					.stream()
					.map(expenseMapper::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Error getting all expenses: " + e.getMessage(), null);
		}
	}

	public void saveAllExpenses(final List<ExpenseDTO> expenses) {
		try {
			List<Expense> expenseEntities = expenses.stream()
					.map(expenseMapper::toEntity)
					.collect(Collectors.toList());

			expenseRepository.saveAll(expenseEntities);
		} catch (Exception e) {
			throw new RuntimeException("Error saving all expenses: " + e.getMessage(), null);
		}
	}

}
