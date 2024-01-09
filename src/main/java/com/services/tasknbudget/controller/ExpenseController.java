package com.services.tasknbudget.controller;

import com.services.tasknbudget.entity.Expense;
import com.services.tasknbudget.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/expense")
	public ResponseEntity<Expense> createExpense(@RequestBody final Expense expense) {
		if (expense != null) {
			Expense newExpense = expenseService.createExpense(expense);
			return ResponseEntity.ok(newExpense);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/expense/{expenseId}")
	public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") final Integer expenseId,
	                                             @RequestBody final Expense expense) {
		if (expenseId != null && expense != null) {
			Optional<Expense> updatedExpense = expenseService.updateExpense(expenseId, expense);
			return updatedExpense.map(ResponseEntity::ok)
					.orElseGet(() -> ResponseEntity.unprocessableEntity().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/expense/{expenseId}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable("expenseId") final Integer expenseId) {
		if (expenseId != null) {
			Optional<Expense> expense = expenseService.getExpenseById(expenseId);
			return expense.map(ResponseEntity::ok)
					.orElseGet(() -> ResponseEntity.notFound().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/expense/{expenseId}")
	public ResponseEntity<String> deleteExpenseById(@PathVariable("expenseId") final Integer expenseId) {
		if (expenseId != null) {
			expenseService.deleteExpenseById(expenseId);
			return ResponseEntity.ok("Expense deleted successfully");
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/expense/all")
	public ResponseEntity<List<Expense>> getAllExpense() {
		List<Expense> expenses = expenseService.getAllExpense();
		return ResponseEntity.ok(expenses);
	}
}
