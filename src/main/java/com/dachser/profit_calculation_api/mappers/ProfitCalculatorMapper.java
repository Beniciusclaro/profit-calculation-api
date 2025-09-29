package com.dachser.profit_calculation_api.mappers;

import com.dachser.profit_calculation_api.entities.domains.ProfitCalculator;
import com.dachser.profit_calculation_api.entities.dtos.ProfitCalculatorResponse;

public class ProfitCalculatorMapper {

    public static ProfitCalculator responseToEntity(ProfitCalculatorResponse profitCalculator){
        return ProfitCalculator.builder()
                .id(null)
                .income(profitCalculator.income())
                .totalCost(profitCalculator.totalCost())
                .profit(profitCalculator.profit())
                .customerId(profitCalculator.customerId())
                .build();
    }

    public static ProfitCalculatorResponse entityToResponse(ProfitCalculator profitCalculator){
        return new ProfitCalculatorResponse(
                profitCalculator.getId(),
                profitCalculator.getIncome(),
                profitCalculator.getTotalCost(),
                profitCalculator.getProfit(),
                profitCalculator.getCustomerId()
        );
    }
}
