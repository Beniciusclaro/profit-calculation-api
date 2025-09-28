package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.domains.Shipment;
import com.dachser.profit_calculation_api.domains.ShipmentResponse;

import java.util.List;

public interface ShipmentService {

    ShipmentResponse saveShipment(Shipment shipment);

    List<ShipmentResponse> findByCustomerId(Long customerId);

    List<ShipmentResponse> findAllShipments();
}
