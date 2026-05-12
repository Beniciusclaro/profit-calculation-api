package com.dachser.profitcalculator.mapper;

import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.entrypoint.dto.ShipmentDto;
import com.dachser.profitcalculator.persistence.entity.ShipmentEntity;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {
    public ShipmentEntity toEntity(ShipmentBO shipment) {
        return ShipmentEntity.builder()
                .id(shipment.getId())
                .income(shipment.getIncome())
                .cost(shipment.getCost())
                .additional_cost(shipment.getAdditional_cost())
                .build();
    }

    public ShipmentBO toBO(ShipmentEntity shipment) {
        return ShipmentBO.builder()
                .id(shipment.getId())
                .income(shipment.getIncome())
                .cost(shipment.getCost())
                .additional_cost(shipment.getAdditional_cost())
                .build();
    }

    public ShipmentBO toBO(ShipmentDto shipment) {
        return ShipmentBO.builder()
                .id(shipment.getId())
                .income(shipment.getIncome())
                .cost(shipment.getCost())
                .additional_cost(shipment.getAdditional_cost())
                .build();
    }

    public ShipmentDto toDto(ShipmentBO shipment) {
        return ShipmentDto.builder()
                .id(shipment.getId())
                .income(shipment.getIncome())
                .cost(shipment.getCost())
                .additional_cost(shipment.getAdditional_cost())
                .build();
    }
}
