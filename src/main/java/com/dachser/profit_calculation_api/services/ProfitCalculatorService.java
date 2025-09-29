package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.entities.dtos.ProfitCalculatorResponse;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentRequest;

import java.util.List;

public interface ProfitCalculatorService {

    void calculateShipment(Long customerId, ShipmentRequest shipmentRequest);

    List<ProfitCalculatorResponse> getAllProfitsByCustomerId(Long customerId);
}
