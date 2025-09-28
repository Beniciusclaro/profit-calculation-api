package com.dachser.profit_calculation_api.domains;

import java.math.BigDecimal;

public record ProfitCalculatorResponse(Long id, BigDecimal income, BigDecimal totalCost, BigDecimal profit, Long customerId) {
}
