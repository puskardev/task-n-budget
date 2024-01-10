package com.services.tasknbudget.mapper;
import com.services.tasknbudget.dto.IncomeDTO;
import com.services.tasknbudget.entity.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
	@Mapping(source = "budgetId", target = "budget.id")
	Income toEntity(IncomeDTO dto);

	@Mapping(source = "budget.id", target = "budgetId")
	IncomeDTO toDto(Income entity);
}
