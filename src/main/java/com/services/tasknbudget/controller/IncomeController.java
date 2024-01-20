package com.services.tasknbudget.controller;

import com.services.tasknbudget.dto.IncomeDTO;
import com.services.tasknbudget.entity.Income;
import com.services.tasknbudget.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@PostMapping("/income")
	public ResponseEntity<IncomeDTO> createIncome(@RequestBody final IncomeDTO income) {
		if (income != null) {
			IncomeDTO newIncome = incomeService.createIncome(income);
			return ResponseEntity.ok(newIncome);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/income/{incomeId}")
	public ResponseEntity<IncomeDTO> updateIncome(@PathVariable("incomeId") final Integer incomeId,
	                                              @RequestBody final IncomeDTO income) {
		if (incomeId != null && income != null) {
			Optional<IncomeDTO> updatedIncome = incomeService.updateIncome(incomeId, income);
			return updatedIncome.map(ResponseEntity::ok)
								.orElseGet(() -> ResponseEntity.unprocessableEntity().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/income/{incomeId}")
	public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable("incomeId") final Integer incomeId) {
		if (incomeId != null) {
			Optional<IncomeDTO> income = incomeService.getIncomeById(incomeId);
			return income.map(ResponseEntity::ok)
					     .orElseGet(() -> ResponseEntity.notFound().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/income/{incomeId}")
	public ResponseEntity<Object> deleteIncomeById(@PathVariable("incomeId") final Integer incomeId) {
		if (incomeId != null) {
			incomeService.deleteIncomeById(incomeId);
			return ResponseEntity.ok(Collections.singletonMap("message", "Income deleted successfully"));
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/income/all")
	public ResponseEntity<List<IncomeDTO>> getAllIncome() {
		List<IncomeDTO> incomes = incomeService.getAllIncome();
		return ResponseEntity.ok(incomes);
	}
}
