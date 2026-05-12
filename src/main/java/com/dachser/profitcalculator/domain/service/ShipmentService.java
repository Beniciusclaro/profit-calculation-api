package com.dachser.profitcalculator.domain.service;

import com.dachser.profitcalculator.domain.bo.ProfitCalculationBO;
import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.persistence.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public ShipmentBO save(ShipmentBO shipment) {
        return shipmentRepository.save(shipment);
    }

    public List<ProfitCalculationBO> calculateProfits() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::buildProfitCalculation)
                .toList();
    }

    private ProfitCalculationBO buildProfitCalculation(ShipmentBO shipment) {
        return ProfitCalculationBO.builder()
                .id(shipment.getId())
                .income(shipment.getIncome())
                .total_costs(this.getTotalCosts(shipment))
                .profit(this.getProfit(shipment))
                .build();
    }

    private BigDecimal getProfit(ShipmentBO shipment) {
        return shipment.getIncome().subtract(this.getTotalCosts(shipment));
    }

    private BigDecimal getTotalCosts(ShipmentBO shipment) {
        return shipment.getCost().add(shipment.getAdditional_cost());
    }
}
