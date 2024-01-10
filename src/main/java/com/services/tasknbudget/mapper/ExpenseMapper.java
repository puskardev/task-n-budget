package com.services.tasknbudget.mapper;
import com.services.tasknbudget.dto.ExpenseDTO;
import com.services.tasknbudget.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
	@Mapping(source = "budgetId", target = "budget.id")
	Expense toEntity(ExpenseDTO dto);

	@Mapping(source = "budget.id", target = "budgetId")
	ExpenseDTO toDto(Expense entity);
}
