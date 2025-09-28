package com.dachser.profit_calculation_api.repository;

import com.dachser.profit_calculation_api.domains.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findByCustomerId(Long customerId);
}
