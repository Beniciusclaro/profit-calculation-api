package com.dachser.profitcalculator.persistence.repository;

import com.dachser.profitcalculator.mapper.ShipmentMapper;
import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentRepository {

    @Autowired
    private ShipmentMapper shipmentMapper;

    @Autowired
    private ShipmentJpaRepository shipmentJpaRepository;

    public ShipmentBO save(ShipmentBO shipment) {
        return shipmentMapper.toBO(shipmentJpaRepository.save(shipmentMapper.toEntity(shipment)));
    }

    public List<ShipmentBO> findAll() {
        return shipmentJpaRepository.findAll()
                .stream()
                .map(entity -> shipmentMapper.toBO(entity))
                .toList();
    }
}
