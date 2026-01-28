package com.example.mortgage.model;

import java.math.BigDecimal;

/**
 * Request payload for mortgage feasibility check.
 */
public record MortgageCheckRequest(BigDecimal income, int maturityPeriod, BigDecimal loanValue, BigDecimal homeValue
) {}
