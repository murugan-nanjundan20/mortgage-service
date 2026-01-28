package com.example.mortgage.model;


/**
 * Represents an interest rate for a given maturity period.
 */
public record InterestRate(
        int maturityPeriod,
        String interestRate,
        String lastUpdate
) {}
