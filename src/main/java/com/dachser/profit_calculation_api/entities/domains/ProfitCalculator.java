package com.dachser.profit_calculation_api.entities.domains;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_profit_calculators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfitCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal income;
    private BigDecimal totalCost;
    private BigDecimal profit;
    private Long customerId;
}
