package com.dachser.profit_calculation_api.domains;

import java.math.BigDecimal;

public record ShipmentResponse(Long id, BigDecimal income, BigDecimal cost, BigDecimal additionalCost, Long customerId) {
    public ShipmentResponse(Shipment shipment) {
        this(shipment.getId(), shipment.getIncome(), shipment.getCost(), shipment.getAdditionalCost(), shipment.getCustomer().getId());
    }
}
