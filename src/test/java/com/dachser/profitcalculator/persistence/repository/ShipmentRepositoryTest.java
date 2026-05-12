package com.dachser.profitcalculator.persistence.repository;

import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.mapper.ShipmentMapper;
import com.dachser.profitcalculator.persistence.entity.ShipmentEntity;
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
class ShipmentRepositoryTest {

    @Mock
    private ShipmentJpaRepository shipmentJpaRepository;

    @Mock
    private ShipmentMapper shipmentMapper;

    @InjectMocks
    private ShipmentRepository shipmentRepository;

    @Test
    void save_ShouldMapAndSaveCorrectly() {
        // Arrange
        ShipmentBO inputBO = createTestShipmentBO();
        ShipmentEntity mappedEntity = createTestShipmentEntity();
        ShipmentEntity savedEntity = createTestShipmentEntity();
        ShipmentBO expectedBO = createTestShipmentBO();

        when(shipmentMapper.toEntity(inputBO)).thenReturn(mappedEntity);
        when(shipmentJpaRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(shipmentMapper.toBO(savedEntity)).thenReturn(expectedBO);

        // Act
        ShipmentBO result = shipmentRepository.save(inputBO);

        // Assert
        assertEquals(expectedBO, result);
        verify(shipmentMapper).toEntity(inputBO);
        verify(shipmentJpaRepository).save(mappedEntity);
        verify(shipmentMapper).toBO(savedEntity);
    }

    @Test
    void findAll_ShouldReturnMappedBO() {
        // Arrange
        ShipmentEntity entity1 = createTestShipmentEntity();
        ShipmentBO bo1 = createTestShipmentBO();

        when(shipmentJpaRepository.findAll()).thenReturn(List.of(entity1));
        when(shipmentMapper.toBO(entity1)).thenReturn(bo1);

        // Act
        List<ShipmentBO> result = shipmentRepository.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(bo1, result.get(0));
        verify(shipmentJpaRepository).findAll();
        verify(shipmentMapper, times(1)).toBO(any(ShipmentEntity.class));
    }

    private ShipmentBO createTestShipmentBO() {
        return ShipmentBO.builder()
                .id(1L)
                .income(new BigDecimal("5000.00"))
                .cost(new BigDecimal("3200.50"))
                .additional_cost(new BigDecimal("150.75"))
                .build();
    }

    private ShipmentEntity createTestShipmentEntity() {
        return ShipmentEntity.builder()
                .id(1L)
                .income(new BigDecimal("5000.00"))
                .cost(new BigDecimal("3200.50"))
                .additional_cost(new BigDecimal("150.75"))
                .build();
    }
}