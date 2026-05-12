package com.dachser.profitcalculator.mapper;

import com.dachser.profitcalculator.domain.bo.ProfitCalculationBO;
import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.entrypoint.dto.ProfitCalculationDto;
import com.dachser.profitcalculator.entrypoint.dto.ShipmentDto;
import com.dachser.profitcalculator.persistence.entity.ShipmentEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfitCalculationMapper {
    public ProfitCalculationDto toDto(ProfitCalculationBO profitCalculation) {
        return ProfitCalculationDto.builder()
                .id(profitCalculation.getId())
                .income(profitCalculation.getIncome())
                .total_costs(profitCalculation.getTotal_costs())
                .profit(profitCalculation.getProfit())
                .build();
    }
}
