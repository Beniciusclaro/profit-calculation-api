package com.dachser.profitcalculator.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "additional_cost")
    private BigDecimal additional_cost;
}
