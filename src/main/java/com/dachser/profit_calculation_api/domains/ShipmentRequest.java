package com.dachser.profit_calculation_api.domains;

import com.dachser.profit_calculation_api.exceptions.InvalidShipmentRequestException;

import java.math.BigDecimal;

public record ShipmentRequest(BigDecimal income, BigDecimal cost, BigDecimal additionalCost) {
    public ShipmentRequest {
        if (income == null || cost == null) {
            throw new InvalidShipmentRequestException("Income and cost must not be null.");
        }
        if (additionalCost == null) {
            additionalCost = BigDecimal.ZERO;
        }
    }
}
