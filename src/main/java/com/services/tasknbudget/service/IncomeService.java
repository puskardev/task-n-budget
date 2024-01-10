package com.services.tasknbudget.service;

import com.services.tasknbudget.dto.IncomeDTO;
import com.services.tasknbudget.entity.Income;
import com.services.tasknbudget.mapper.IncomeMapper;
import com.services.tasknbudget.repository.IncomeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private IncomeMapper incomeMapper;

	public IncomeDTO createIncome(final IncomeDTO income) {
		try {
			Income entity = incomeMapper.toEntity(income);
			return incomeMapper.toDto(incomeRepository.save(entity));
		} catch (Exception e) {
			throw new RuntimeException("Error creating income: " + e.getMessage(), null);
		}
	}

	public Optional<IncomeDTO> updateIncome(final Integer incomeId, final IncomeDTO newIncome) {
		try {
			return incomeRepository.findById(incomeId)
					.map(income -> {
						Income entity = incomeMapper.toEntity(newIncome);
						entity.setIncomeId(incomeId);
						Income savedIncome = incomeRepository.save(entity);
						return incomeMapper.toDto(savedIncome);
					});
		} catch (Exception e) {
			throw new RuntimeException("Update failed: " + e.getMessage(), null);
		}
	}

	public Optional<IncomeDTO> getIncomeById(final Integer incomeId) {
		try {
			return incomeRepository.findById(incomeId)
					.map(incomeMapper::toDto);
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

	public List<IncomeDTO> getAllIncome() {
		try {
			return incomeRepository.findAll()
					.stream()
					.map(incomeMapper::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Error getting all Income: " + e.getMessage(), null);
		}
	}

}
