package com.example.mortgage.service;

import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Handles mortgage feasibility and monthly cost calculations.
 */
@Service
public class MortgageService {

    private static final double MAX_MULTIPLE_OF_INCOME = 4.0;

    private static final double DEFAULT_ANNUAL_INTEREST_RATE = 0.03; // 3%

    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {

        boolean feasible =
                request.loanValue()
                        .compareTo(
                                request.income()
                                        .multiply(BigDecimal.valueOf(MAX_MULTIPLE_OF_INCOME))
                        ) <= 0
                        && request.loanValue()
                        .compareTo(request.homeValue()) <= 0;

        double monthlyCosts = getMonthlyCosts(request, feasible);

        return new MortgageCheckResponse(feasible, monthlyCosts);
    }

    private static double getMonthlyCosts(MortgageCheckRequest request, boolean feasible) {
        double monthlyCosts = 0.0;

        if (feasible) {
            // Monthly payment check using formula
            double P = request.loanValue().doubleValue();
            double r = DEFAULT_ANNUAL_INTEREST_RATE / 12; // monthly rate
            int n = request.maturityPeriod() * 12; // total months

            double numerator = r * Math.pow(1 + r, n);
            double denominator = Math.pow(1 + r, n) - 1;

            monthlyCosts = P * numerator / denominator;
            monthlyCosts = Math.round(monthlyCosts * 100.0) / 100.0; // round to 2 decimals
        }
        return monthlyCosts;
    }
}
