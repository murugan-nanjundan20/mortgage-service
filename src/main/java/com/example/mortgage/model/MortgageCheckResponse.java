package com.example.mortgage.model;

import java.math.BigDecimal;

/**
 * Response for mortgage feasibility check.
 */
public record MortgageCheckResponse(
        boolean feasible,
        double monthlyCosts
) {
}
