package com.dachser.profitcalculator.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProfitCalculationBO {
    private Long id;
    private BigDecimal income;
    private BigDecimal total_costs;
    private BigDecimal profit;
}
