package com.example.mortgage.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an interest rate for a given maturity period.
 */


@Entity
public class InterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer maturityPeriod;
    private BigDecimal interestRate;
    private LocalDateTime lastUpdate;

    protected InterestRate() {}

    public InterestRate(
            Integer maturityPeriod,
            BigDecimal interestRate,
            LocalDateTime lastUpdate) {
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdate = lastUpdate;
    }

    public Integer getMaturityPeriod() { return maturityPeriod; }
    public BigDecimal getInterestRate() { return interestRate; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
}
