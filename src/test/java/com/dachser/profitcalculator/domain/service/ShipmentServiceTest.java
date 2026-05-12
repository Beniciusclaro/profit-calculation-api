package com.dachser.profitcalculator.domain.service;

import com.dachser.profitcalculator.domain.bo.ProfitCalculationBO;
import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.persistence.repository.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    void save_ShouldDelegateToRepository() {
        final ShipmentBO testShipment = ShipmentBO.builder()
                .id(1L)
                .income(new BigDecimal("5000.00"))
                .cost(new BigDecimal("3200.50"))
                .additional_cost(new BigDecimal("150.75"))
                .build();
        when(shipmentRepository.save(testShipment)).thenReturn(testShipment);

        ShipmentBO result = shipmentService.save(testShipment);

        verify(shipmentRepository, times(1)).save(testShipment);
        assertEquals(testShipment, result);
    }

    @Test
    void calculateProfits_ShouldReturnCorrectCalculations() {
        List<ShipmentBO> mockShipments = List.of(
                ShipmentBO.builder()
                        .id(1L)
                        .income(new BigDecimal("5000.00"))
                        .cost(new BigDecimal("3200.50"))
                        .additional_cost(new BigDecimal("150.75"))
                        .build(),
                ShipmentBO.builder()
                        .id(2L)
                        .income(new BigDecimal("1200.00"))
                        .cost(new BigDecimal("1100.00"))
                        .additional_cost(new BigDecimal("50.00"))
                        .build()
        );

        when(shipmentRepository.findAll()).thenReturn(mockShipments);

        List<ProfitCalculationBO> results = shipmentService.calculateProfits();

        assertEquals(2, results.size());

        // Verify first shipment
        ProfitCalculationBO firstResult = results.get(0);
        assertEquals(1L, firstResult.getId());
        assertEquals(new BigDecimal("5000.00"), firstResult.getIncome());
        assertEquals(new BigDecimal("3351.25"), firstResult.getTotal_costs()); // 3200.50 + 150.75
        assertEquals(new BigDecimal("1648.75"), firstResult.getProfit()); // 5000.00 - 3351.25

        // Verify second shipment
        ProfitCalculationBO secondResult = results.get(1);
        assertEquals(2L, secondResult.getId());
        assertEquals(new BigDecimal("1200.00"), secondResult.getIncome());
        assertEquals(new BigDecimal("1150.00"), secondResult.getTotal_costs()); // 1100.00 + 50.00
        assertEquals(new BigDecimal("50.00"), secondResult.getProfit()); // 1200.00 - 1150.00

        verify(shipmentRepository, times(1)).findAll();
    }
}