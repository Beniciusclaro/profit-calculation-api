package com.dachser.profitcalculator.entrypoint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProfitCalculationDto {
    private Long id;
    private BigDecimal income;
    private BigDecimal total_costs;
    private BigDecimal profit;
}
