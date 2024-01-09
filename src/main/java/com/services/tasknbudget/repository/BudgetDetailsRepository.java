package com.services.tasknbudget.repository;

import com.services.tasknbudget.entity.Budget;
import com.services.tasknbudget.entity.BudgetDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetDetailsRepository extends JpaRepository<BudgetDetails, Integer> {
}
