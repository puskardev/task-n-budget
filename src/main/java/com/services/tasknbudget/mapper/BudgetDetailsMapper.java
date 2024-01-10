package com.services.tasknbudget.mapper;

import com.services.tasknbudget.dto.BudgetDetailsDTO;
import com.services.tasknbudget.entity.BudgetDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetDetailsMapper {
	@Mapping(source = "budgetId", target = "budget.id")
	BudgetDetails toEntity(BudgetDetailsDTO dto);

	@Mapping(source = "budget.id", target = "budgetId")
	BudgetDetailsDTO toDto(BudgetDetails entity);
}
