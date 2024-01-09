package com.services.tasknbudget.service;

import com.services.tasknbudget.entity.Income;
import com.services.tasknbudget.repository.IncomeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	public Income createIncome(final Income income) {
		try {
			return incomeRepository.save(income);
		} catch (Exception e) {
			throw new RuntimeException("Error creating income: " + e.getMessage(), null);
		}
	}

	public Optional<Income> updateIncome(final Integer incomeId, final Income newIncome) {
		try {
			return incomeRepository.findById(incomeId)
					.map(income -> {
						BeanUtils.copyProperties(income, newIncome);
						income.setIncomeId(incomeId);
						return incomeRepository.save(income);
					});
		} catch (Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage(), null);
		}
	}

	public Optional<Income> getIncomeById(final Integer incomeId) {
		try {
			return incomeRepository.findById(incomeId);
		} catch (Exception e) {
			throw new RuntimeException("Income Not Found: " + e.getMessage(), null);
		}
	}

	public void deleteIncomeById(final Integer incomeId) {
		try {
			incomeRepository.deleteById(incomeId);
		} catch (Exception e) {
			throw new RuntimeException("Delete Income Failed: " + e.getMessage(), null);
		}
	}

	public List<Income> getAllIncome() {
		try {
			return incomeRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Error getting all Income: " + e.getMessage(), null);
		}
	}

}
