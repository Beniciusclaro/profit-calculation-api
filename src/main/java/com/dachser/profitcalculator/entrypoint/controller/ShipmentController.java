package com.dachser.profitcalculator.entrypoint.controller;

import com.dachser.profitcalculator.domain.service.ShipmentService;
import com.dachser.profitcalculator.entrypoint.dto.ProfitCalculationDto;
import com.dachser.profitcalculator.entrypoint.dto.ShipmentDto;
import com.dachser.profitcalculator.mapper.ProfitCalculationMapper;
import com.dachser.profitcalculator.mapper.ShipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@CrossOrigin(origins = "http://localhost:4200")
public class ShipmentController {
    
    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentMapper shipmentMapper;

    @Autowired
    private ProfitCalculationMapper profitCalculationMapper;

    @GetMapping("/profits")
    public List<ProfitCalculationDto> getShipments() {
        return shipmentService.calculateProfits()
                .stream()
                .map(profitCalculationMapper::toDto)
                .toList();
    }

    @PostMapping
    public ShipmentDto createShipment(@RequestBody ShipmentDto shipment) {
        return shipmentMapper.toDto(shipmentService.save(shipmentMapper.toBO(shipment)));
    }
}
