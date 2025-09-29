package com.dachser.profit_calculation_api.controllers;

import com.dachser.profit_calculation_api.entities.dtos.ProfitCalculatorResponse;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentRequest;
import com.dachser.profit_calculation_api.services.ProfitCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers/{customerId}/shipments")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfitCalculatorController {

    private final ProfitCalculatorService profitCalculatorService;

    public ProfitCalculatorController(ProfitCalculatorService profitCalculatorService) {
        this.profitCalculatorService = profitCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Void> calculateAndSaveShipment(
            @PathVariable Long customerId,
            @RequestBody ShipmentRequest shipmentRequest) {

        profitCalculatorService.calculateShipment(customerId, shipmentRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profits")
    public ResponseEntity<List<ProfitCalculatorResponse>> getAllProfits(@PathVariable Long customerId) {
        return ResponseEntity.ok(profitCalculatorService.getAllProfitsByCustomerId(customerId));
    }
}
