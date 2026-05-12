package com.dachser.profitcalculator.mapper;

import com.dachser.profitcalculator.domain.bo.ShipmentBO;
import com.dachser.profitcalculator.entrypoint.dto.ShipmentDto;
import com.dachser.profitcalculator.persistence.entity.ShipmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShipmentMapperTest {

    @InjectMocks
    private ShipmentMapper shipmentMapper;

    private final ShipmentEntity testEntity = ShipmentEntity.builder()
            .id(1L)
            .income(new BigDecimal("5000.00"))
            .cost(new BigDecimal("3200.50"))
            .additional_cost(new BigDecimal("150.75"))
            .build();

    private final ShipmentBO testBO = ShipmentBO.builder()
            .id(1L)
            .income(new BigDecimal("5000.00"))
            .cost(new BigDecimal("3200.50"))
            .additional_cost(new BigDecimal("150.75"))
            .build();

    private final ShipmentDto testDto = ShipmentDto.builder()
            .id(1L)
            .income(new BigDecimal("5000.00"))
            .cost(new BigDecimal("3200.50"))
            .additional_cost(new BigDecimal("150.75"))
            .build();

    @Test
    void toEntity_ShouldMapAllFieldsFromBO() {
        ShipmentEntity result = shipmentMapper.toEntity(testBO);

        assertNotNull(result);
        assertEquals(testBO.getId(), result.getId());
        assertEquals(testBO.getIncome(), result.getIncome());
        assertEquals(testBO.getCost(), result.getCost());
        assertEquals(testBO.getAdditional_cost(), result.getAdditional_cost());
    }

    @Test
    void toBO_ShouldMapAllFieldsFromEntity() {
        ShipmentBO result = shipmentMapper.toBO(testEntity);

        assertNotNull(result);
        assertEquals(testEntity.getId(), result.getId());
        assertEquals(testEntity.getIncome(), result.getIncome());
        assertEquals(testEntity.getCost(), result.getCost());
        assertEquals(testEntity.getAdditional_cost(), result.getAdditional_cost());
    }

    @Test
    void toBO_ShouldMapAllFieldsFromDto() {
        ShipmentBO result = shipmentMapper.toBO(testDto);

        assertNotNull(result);
        assertEquals(testDto.getId(), result.getId());
        assertEquals(testDto.getIncome(), result.getIncome());
        assertEquals(testDto.getCost(), result.getCost());
        assertEquals(testDto.getAdditional_cost(), result.getAdditional_cost());
    }

    @Test
    void toDto_ShouldMapAllFieldsFromBO() {
        ShipmentDto result = shipmentMapper.toDto(testBO);

        assertNotNull(result);
        assertEquals(testBO.getId(), result.getId());
        assertEquals(testBO.getIncome(), result.getIncome());
        assertEquals(testBO.getCost(), result.getCost());
        assertEquals(testBO.getAdditional_cost(), result.getAdditional_cost());
    }

    @Test
    void mapping_ShouldHandleZeroValues() {
        ShipmentBO zeroBO = ShipmentBO.builder()
                .id(2L)
                .income(BigDecimal.ZERO)
                .cost(BigDecimal.ZERO)
                .additional_cost(BigDecimal.ZERO)
                .build();

        ShipmentEntity entity = shipmentMapper.toEntity(zeroBO);
        assertEquals(BigDecimal.ZERO, entity.getIncome());
        assertEquals(BigDecimal.ZERO, entity.getCost());

        ShipmentDto dto = shipmentMapper.toDto(zeroBO);
        assertEquals(BigDecimal.ZERO, dto.getIncome());
    }

    @Test
    void mapping_ShouldHandleNullId() {
        ShipmentBO noIdBO = ShipmentBO.builder()
                .income(new BigDecimal("100.00"))
                .cost(new BigDecimal("50.00"))
                .additional_cost(BigDecimal.ZERO)
                .build();

        ShipmentEntity entity = shipmentMapper.toEntity(noIdBO);
        ShipmentDto dto = shipmentMapper.toDto(noIdBO);

        assertNull(entity.getId());
        assertNull(dto.getId());
    }
}