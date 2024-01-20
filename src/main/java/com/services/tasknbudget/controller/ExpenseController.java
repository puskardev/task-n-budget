package com.services.tasknbudget.controller;

import com.services.tasknbudget.dto.ExpenseDTO;
import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/expense")
	public ResponseEntity<ExpenseDTO> createExpense(@RequestBody final ExpenseDTO expense) {
		if (expense != null) {
			ExpenseDTO newExpense = expenseService.createExpense(expense);
			return ResponseEntity.ok(newExpense);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/expense/{expenseId}")
	public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("expenseId") final Integer expenseId,
	                                             @RequestBody final ExpenseDTO expense) {
		if (expenseId != null && expense != null) {
			Optional<ExpenseDTO> updatedExpense = expenseService.updateExpense(expenseId, expense);
			return updatedExpense.map(ResponseEntity::ok)
					.orElseGet(() -> ResponseEntity.unprocessableEntity().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/expense/{expenseId}")
	public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable("expenseId") final Integer expenseId) {
		if (expenseId != null) {
			Optional<ExpenseDTO> expense = expenseService.getExpenseById(expenseId);
			return expense.map(ResponseEntity::ok)
					.orElseGet(() -> ResponseEntity.notFound().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/expense/{expenseId}")
	public ResponseEntity<Object> deleteExpenseById(@PathVariable("expenseId") final Integer expenseId) {
		if (expenseId != null) {
			expenseService.deleteExpenseById(expenseId);
			return ResponseEntity.ok(Collections.singletonMap("message", "Expense deleted successfully"));
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/expense/all")
	public ResponseEntity<List<ExpenseDTO>> getAllExpense() {
		List<ExpenseDTO> expenses = expenseService.getAllExpense();
		return ResponseEntity.ok(expenses);
	}

	@PostMapping("/expense/all")
	public ResponseEntity<String> getAllExpenseByBudgetId(@RequestBody final List<ExpenseDTO> expenses) {
		if (expenses != null && !expenses.isEmpty()) {
			expenseService.saveAllExpenses(expenses);
			return ResponseEntity.ok("Expenses saved successfully");
		}
		return ResponseEntity.badRequest().build();
	}
}
