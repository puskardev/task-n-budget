package com.services.tasknbudget.service;

import com.services.tasknbudget.dto.BudgetDetailsDTO;
import com.services.tasknbudget.entity.BudgetDetails;
import com.services.tasknbudget.mapper.BudgetDetailsMapper;
import com.services.tasknbudget.repository.BudgetDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetDetailsService {

	@Autowired
	private BudgetDetailsRepository budgetDetailsRepository;

	@Autowired
	private BudgetDetailsMapper budgetDetailsMapper;

	public BudgetDetailsDTO createBudgetDetails(final BudgetDetailsDTO budgetDetails) {
		try {
			BudgetDetails entity = budgetDetailsMapper.toEntity(budgetDetails);
			return budgetDetailsMapper.toDto(budgetDetailsRepository.save(entity));
		} catch (Exception e) {
			throw new RuntimeException("Error creating budgetDetails: " + e.getMessage(), null);
		}
	}

	public BudgetDetailsDTO updateBudgetDetails(final Integer budgetDetailsId, final BudgetDetailsDTO newBudgetDetails) {
		try {
			return budgetDetailsRepository.findById(budgetDetailsId)
					.map(budgetDetails -> {
						BudgetDetails entity = budgetDetailsMapper.toEntity(newBudgetDetails);
						entity.setBudgetDetailsId(budgetDetailsId);
						BudgetDetails savedBudgetDetails = budgetDetailsRepository.save(entity);
						return budgetDetailsMapper.toDto(savedBudgetDetails);
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
