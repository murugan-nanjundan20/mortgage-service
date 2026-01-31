package com.example.mortgage.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an interest rate for a given maturity period.
 */

@Entity
@Table(name = "interest_rate")
public class InterestRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maturityPeriod;
    private BigDecimal interestRate;
    private LocalDateTime lastUpdate;

    protected InterestRateEntity() {}

    public InterestRateEntity(int maturityPeriod, BigDecimal interestRate) {
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdate = LocalDateTime.now();
    }

    public int getMaturityPeriod() { return maturityPeriod; }
    public BigDecimal getInterestRate() { return interestRate; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
}
