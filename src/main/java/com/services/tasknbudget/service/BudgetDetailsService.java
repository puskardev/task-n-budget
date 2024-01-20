package com.services.tasknbudget.service;

import com.services.tasknbudget.constants.ExpenseConstants;
import com.services.tasknbudget.dto.BudgetDetailsDTO;
import com.services.tasknbudget.dto.ExpenseDTO;
import com.services.tasknbudget.dto.IncomeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetDetailsService {
	public BudgetDetailsDTO getBudgetDetails(Integer id, List<ExpenseDTO> expenses, List<IncomeDTO> income) {
		BudgetDetailsDTO budgetDetails = new BudgetDetailsDTO(id);

		budgetDetails.setTotalExpenses(expenses.stream()
				.mapToDouble(ExpenseDTO::getAmount)
				.sum());

		budgetDetails.setTotalIncome(income.stream()
				.mapToDouble(IncomeDTO::getAmount)
				.sum());

		budgetDetails.setTotalMiscExpenses(expenses.stream()
				.filter(expense -> expense.getExpenseCategoryType().equals(ExpenseConstants.EXPENSE_TYPE_MISC))
				.mapToDouble(ExpenseDTO::getAmount)
				.sum());

		budgetDetails.setTotalCreditCardExpenses(expenses.stream()
				.filter(expense -> expense.getExpenseCategoryType().equals(ExpenseConstants.EXPENSE_TYPE_CREDIT_CARD))
				.mapToDouble(ExpenseDTO::getAmount)
				.sum());

		budgetDetails.setTotalSubscriptionExpenses(expenses.stream()
				.filter(expense -> expense.getExpenseCategoryType().equals(ExpenseConstants.EXPENSE_TYPE_SUBSCRIPTION))
				.mapToDouble(ExpenseDTO::getAmount)
				.sum());

		budgetDetails.setTotalSavings(budgetDetails.getTotalIncome() - budgetDetails.getTotalExpenses());

		return budgetDetails;
	}
}
