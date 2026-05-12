package com.dachser.profit_calculation_api.repository;

import com.dachser.profit_calculation_api.entities.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
