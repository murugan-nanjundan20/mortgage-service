package com.example.mortgage.model;

/**
 * Response for mortgage feasibility check.
 */
public record MortgageCheckResponse(
        boolean feasible,
        double monthlyCosts
) {
}
