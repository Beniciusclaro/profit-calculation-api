package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.entities.domains.Customer;
import com.dachser.profit_calculation_api.entities.domains.ProfitCalculator;
import com.dachser.profit_calculation_api.entities.dtos.ProfitCalculatorResponse;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentRequest;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentResponse;
import com.dachser.profit_calculation_api.exceptions.CustomerException;
import com.dachser.profit_calculation_api.repository.CustomerRepository;
import com.dachser.profit_calculation_api.repository.ProfitCalculatorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfitCalculatorServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ShipmentService shipmentService;

    @Mock
    private ProfitCalculatorRepository profitCalculatorRepository;
    @InjectMocks
    private ProfitCalculatorServiceImpl service;

    @Test
    void testCalculateShipment_Success() {
        Long customerId = 1L;
        Customer customer = new Customer();
        ShipmentRequest shipmentRequest = new ShipmentRequest(
                new BigDecimal("100.00"),
                new BigDecimal("60.00"),
                new BigDecimal("10.00")
        );

        ShipmentResponse shipmentResponse = new ShipmentResponse(
                10L,
                new BigDecimal("100.00"),
                new BigDecimal("60.00"),
                new BigDecimal("10.00"),
                1L
        );

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(shipmentService.saveShipment(any())).thenReturn(shipmentResponse);

        service.calculateShipment(customerId, shipmentRequest);

        verify(customerRepository).findById(customerId);
        verify(shipmentService).saveShipment(any());
        verify(profitCalculatorRepository).save(any());
    }

    @Test
    void testCalculateShipment_CustomerNotFound() {
        Long customerId = 2L;
        ShipmentRequest shipmentRequest = new ShipmentRequest(
                new BigDecimal("100.00"),
                new BigDecimal("60.00"),
                new BigDecimal("10.00"));

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.calculateShipment(customerId, shipmentRequest)
        );
        assertEquals("This customer doesn't exist!", exception.getMessage());
    }

    @Test
    void testCalculateShipment_CustomerNotFound_ShouldThrowException() {
        Long customerId = 99L;
        ShipmentRequest shipmentRequest = new ShipmentRequest(
                new BigDecimal("100.00"),
                new BigDecimal("60.00"),
                new BigDecimal("10.00")
        );

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerException.class, () ->
                service.calculateShipment(customerId, shipmentRequest)
        );

        verify(shipmentService, never()).saveShipment(any());
        verify(profitCalculatorRepository, never()).save(any());
    }

    @Test
    void testGetAllProfitsByCustomerId_ShouldReturnMappedResponses() {
        Long customerId = 1L;

        ProfitCalculator entity1 = ProfitCalculator.builder()
                .id(10L)
                .income(new BigDecimal("100.00"))
                .totalCost(new BigDecimal("60.00"))
                .profit(new BigDecimal("40.00"))
                .customerId(customerId)
                .build();

        ProfitCalculator entity2 = ProfitCalculator.builder()
                .id(11L)
                .income(new BigDecimal("200.00"))
                .totalCost(new BigDecimal("120.00"))
                .profit(new BigDecimal("80.00"))
                .customerId(customerId)
                .build();

        when(profitCalculatorRepository.getAllProfitsByCustomerId(customerId))
                .thenReturn(List.of(entity1, entity2));

        List<ProfitCalculatorResponse> responses = service.getAllProfitsByCustomerId(customerId);

        assertEquals(2, responses.size());
        assertEquals(new BigDecimal("100.00"), responses.get(0).income());
        assertEquals(new BigDecimal("80.00"), responses.get(1).profit());
    }
}
