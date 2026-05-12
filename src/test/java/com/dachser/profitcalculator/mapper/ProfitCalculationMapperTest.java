package com.dachser.profitcalculator.mapper;

import com.dachser.profitcalculator.domain.bo.ProfitCalculationBO;
import com.dachser.profitcalculator.entrypoint.dto.ProfitCalculationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProfitCalculationMapperTest {

    @InjectMocks
    private ProfitCalculationMapper mapper;

    private final ProfitCalculationBO testBO = ProfitCalculationBO.builder()
            .id(1L)
            .income(new BigDecimal("5000.00"))
            .total_costs(new BigDecimal("3351.25"))
            .profit(new BigDecimal("1648.75"))
            .build();

    @Test
    void toDto_ShouldMapAllFieldsCorrectly() {
        ProfitCalculationDto result = mapper.toDto(testBO);

        assertNotNull(result);
        assertEquals(testBO.getId(), result.getId());
        assertEquals(testBO.getIncome(), result.getIncome());
        assertEquals(testBO.getTotal_costs(), result.getTotal_costs());
        assertEquals(testBO.getProfit(), result.getProfit());
    }
}