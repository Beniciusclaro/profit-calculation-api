package com.dachser.profit_calculation_api.repository;

import com.dachser.profit_calculation_api.entities.domains.ProfitCalculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitCalculatorRepository extends JpaRepository<ProfitCalculator, Long> {

    List<ProfitCalculator> getAllProfitsByCustomerId(Long customerId);
}
