package com.services.tasknbudget.controller;

import com.services.tasknbudget.dto.BudgetDTO;
import com.services.tasknbudget.entity.Budget;

import com.services.tasknbudget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/budget/{budgetDate}")
    public ResponseEntity<BudgetDTO> getBudgetByDate(@PathVariable("budgetDate") final String budgetDate) {
        if (budgetDate != null && !budgetDate.isEmpty()) {
            BudgetDTO budget = this.budgetService.getBudgetByDate(budgetDate);
            return ResponseEntity.ok(budget);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/budget")
    public ResponseEntity<BudgetDTO> createBudget(@RequestBody final String budgetDate) {
        if (budgetDate != null && !budgetDate.isEmpty()) {
            BudgetDTO newBudget = this.budgetService.createBudget(budgetDate);
            return ResponseEntity.ok(newBudget);
        }
        return ResponseEntity.badRequest().build();
    }
}
