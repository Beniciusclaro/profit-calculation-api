package com.dachser.profitcalculator.entrypoint.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ShipmentDto {
    private Long id;
    private BigDecimal income;
    private BigDecimal cost;
    private BigDecimal additional_cost;
}
