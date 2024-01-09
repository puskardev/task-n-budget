package com.services.tasknbudget.controller;

import com.services.tasknbudget.entity.Income;
import com.services.tasknbudget.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@PostMapping("/income")
	public ResponseEntity<Income> createIncome(final Income income) {
		if (income != null) {
			Income newIncome = incomeService.createIncome(income);
			return ResponseEntity.ok(newIncome);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/income/{incomeId}")
	public ResponseEntity<Income> updateIncome(final Integer incomeId, final Income income) {
		if (incomeId != null && income != null) {
			Optional<Income> updatedIncome = incomeService.updateIncome(incomeId, income);
			return updatedIncome.map(ResponseEntity::ok)
								.orElseGet(() -> ResponseEntity.unprocessableEntity().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/income/{incomeId}")
	public ResponseEntity<Income> getIncomeById(final Integer incomeId) {
		if (incomeId != null) {
			Optional<Income> income = incomeService.getIncomeById(incomeId);
			return income.map(ResponseEntity::ok)
					     .orElseGet(() -> ResponseEntity.notFound().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/income/{incomeId}")
	public ResponseEntity<String> deleteIncomeById(final Integer incomeId) {
		if (incomeId != null) {
			incomeService.deleteIncomeById(incomeId);
			return ResponseEntity.ok("Income deleted successfully");
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/income/all")
	public ResponseEntity<List<Income>> getAllIncome() {
		List<Income> incomes = incomeService.getAllIncome();
		return ResponseEntity.ok(incomes);
	}
}
