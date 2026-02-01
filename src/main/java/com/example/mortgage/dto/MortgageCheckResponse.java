package com.example.mortgage.dto;


/**
 * Response for mortgage feasibility check.
 */
public record MortgageCheckResponse(
        boolean feasible,
        double monthlyCosts
) {
}
