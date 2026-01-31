package com.example.mortgage.exception;

/**
 * Thrown when no interest rate is found for a given maturity period.
 */
public class InterestRateNotFoundException extends RuntimeException {

    public InterestRateNotFoundException(String maturityPeriod) {
        super("No interest rate found for maturity period: " + maturityPeriod);
    }
}
