package com.services.tasknbudget.controller;

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
    public ResponseEntity<Budget> getBudgetByDate(@PathVariable("budgetDate") final String budgetDate) {
        if (budgetDate != null && !budgetDate.isEmpty()) {
            Budget budget = this.budgetService.getBudgetByDate(budgetDate);
            return ResponseEntity.ok(budget);
        }
        return ResponseEntity.badRequest().build();
    }
}
