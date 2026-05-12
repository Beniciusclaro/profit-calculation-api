package com.dachser.profitcalculator.persistence.repository;

import com.dachser.profitcalculator.persistence.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentJpaRepository extends JpaRepository<ShipmentEntity, Long> {
}
