package com.dachser.profit_calculation_api.services;

import com.dachser.profit_calculation_api.entities.domains.Customer;
import com.dachser.profit_calculation_api.entities.domains.Shipment;
import com.dachser.profit_calculation_api.entities.dtos.ShipmentResponse;
import com.dachser.profit_calculation_api.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceImplTest {
    @Mock
    private ShipmentRepository shipmentRepository;
    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @BeforeEach
    void setUp() {
        shipmentRepository = mock(ShipmentRepository.class);
        shipmentService = new ShipmentServiceImpl(shipmentRepository);
    }

    @Test
    void testSaveShipment() {
        new Customer();
        Shipment shipment = Shipment.builder()
                .income(new BigDecimal("100.00"))
                .cost(new BigDecimal("100.00"))
                .customer(Customer.builder().id(1L).name("Benicius").build())
                .build();

        Shipment shipmentSaved = Shipment.builder()
                .id(1L)
                .income(new BigDecimal("100.00"))
                .cost(new BigDecimal("100.00"))
                .customer(Customer.builder().id(1L).name("Benicius").build())
                .build();

        when(shipmentRepository.save(shipment)).thenReturn(shipmentSaved);

        ShipmentResponse response = shipmentService.saveShipment(shipment);
        verify(shipmentRepository, times(1)).save(shipment);
        assertNotNull(response);
        assertEquals(shipmentSaved.getId(), response.id());
    }

    @Test
    void testFindByCustomerId_ReturnsMappedResponses() {
        Long customerId = 1L;
        Shipment shipment1 = Shipment.builder()
                .id(1L)
                .income(new BigDecimal("100.00"))
                .cost(new BigDecimal("100.00"))
                .customer(Customer.builder().id(1L).name("Benicius").build())
                .build();
        Shipment shipment2 = Shipment.builder()
                .id(2L)
                .income(new BigDecimal("100.00"))
                .cost(new BigDecimal("100.00"))
                .customer(Customer.builder().id(2L).name("Benicius").build())
                .build();

        when(shipmentRepository.findByCustomerId(customerId)).thenReturn(List.of(shipment1, shipment2));

        List<ShipmentResponse> responses = shipmentService.findByCustomerId(customerId);

        assertEquals(2, responses.size());
        assertNotNull(responses.getFirst());
        verify(shipmentRepository, times(1)).findByCustomerId(customerId);
    }
}
