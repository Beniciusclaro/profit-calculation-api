package com.dachser.profit_calculation_api.mappers;

import com.dachser.profit_calculation_api.domains.Customer;
import com.dachser.profit_calculation_api.domains.Shipment;
import com.dachser.profit_calculation_api.domains.ShipmentRequest;
import com.dachser.profit_calculation_api.domains.ShipmentResponse;

public class ShipmentMapper {

    public static Shipment requestToEntity(ShipmentRequest shipmentRequest, Customer customer){
        return Shipment.builder()
                .id(null)
                .customer(Customer.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .build())
                .additionalCost(shipmentRequest.additionalCost())
                .cost(shipmentRequest.cost())
                .income(shipmentRequest.income())
                .build();
    }

    public static ShipmentResponse entityToResponse(Shipment shipment){
        return new ShipmentResponse(
                shipment.getId(),
                shipment.getIncome(),
                shipment.getCost(),
                shipment.getAdditionalCost(),
                shipment.getCustomer().getId()
        );
    }

}
