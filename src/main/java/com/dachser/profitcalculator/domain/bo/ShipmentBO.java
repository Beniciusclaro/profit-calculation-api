package com.dachser.profitcalculator.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ShipmentBO {
    private Long id;
    private BigDecimal income;
    private BigDecimal cost;
    private BigDecimal additional_cost;
}
