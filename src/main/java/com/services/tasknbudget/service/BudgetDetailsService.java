package com.services.tasknbudget.service;

import com.services.tasknbudget.entity.BudgetDetails;
import com.services.tasknbudget.repository.BudgetDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetDetailsService {

	@Autowired
	private BudgetDetailsRepository budgetDetailsRepository;

	public BudgetDetails createBudgetDetails(final BudgetDetails budgetDetails) {
		try {
			return budgetDetailsRepository.save(budgetDetails);
		} catch (Exception e) {
			throw new RuntimeException("Error creating budgetDetails: " + e.getMessage(), null);
		}
	}

	public BudgetDetails updateBudgetDetails(final Integer budgetDetailsId, final BudgetDetails newBudgetDetails) {
		try {
			return budgetDetailsRepository.findById(budgetDetailsId)
					.map(budgetDetails -> {
						BeanUtils.copyProperties(budgetDetails, newBudgetDetails);
						budgetDetails.setBudgetDetailsId(budgetDetailsId);
						return budgetDetailsRepository.save(budgetDetails);
					}).orElseThrow(() -> new RuntimeException("BudgetDetails Not Found: " + budgetDetailsId));
		} catch (Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage(), null);
		}
	}

	public BudgetDetails getBudgetDetailsById(final Integer budgetDetailsId) {
		return budgetDetailsRepository.findById(budgetDetailsId)
				.orElseThrow(() -> new RuntimeException("BudgetDetails Not Found: " + budgetDetailsId));
	}
}
