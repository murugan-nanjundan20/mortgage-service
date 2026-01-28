package com.example.mortgage.model;

import java.math.BigDecimal;

/**
 * Represents an interest rate for a given maturity period.
 */
public record InterestRate(
        int maturityPeriod,
        String interestRate,
        String lastUpdate
) {}
