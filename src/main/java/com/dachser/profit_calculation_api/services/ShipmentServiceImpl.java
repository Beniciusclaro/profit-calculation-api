package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.domains.Shipment;
import com.dachser.profit_calculation_api.domains.ShipmentResponse;
import com.dachser.profit_calculation_api.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dachser.profit_calculation_api.mappers.ShipmentMapper.entityToResponse;

@Service
public class ShipmentServiceImpl implements ShipmentService{


    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public ShipmentResponse saveShipment(Shipment shipment) {
        Shipment shipmentSaved = shipmentRepository.save(shipment);
        return entityToResponse(shipmentSaved);
    }

    @Override
    public List<ShipmentResponse> findByCustomerId(Long customerId) {
        return shipmentRepository.findByCustomerId(customerId).stream().map(ShipmentResponse::new).toList();
    }

    @Override
    public List<ShipmentResponse> findAllShipments() {
        return shipmentRepository
                .findAll()
                .stream()
                .map(ShipmentResponse::new)
                .toList();
    }
}
