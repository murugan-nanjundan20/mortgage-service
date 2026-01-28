package com.example.mortgage.service;

import com.example.mortgage.model.InterestRate;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.repository.InterestRateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles mortgage feasibility and monthly cost calculations.
 */
@Service
public class MortgageService {


    private final InterestRateRepository rateRepository;

    public MortgageService(InterestRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {

        boolean feasible =
                request.loanValue().compareTo(
                        request.income().multiply(BigDecimal.valueOf(4))
                ) <= 0
                        && request.loanValue().compareTo(request.homeValue()) <= 0;

        BigDecimal monthlyCosts = BigDecimal.ZERO;

        if (feasible) {
            InterestRate rate = rateRepository
                    .findByMaturityPeriod(request.maturityPeriod())
                    .orElseThrow();

            BigDecimal yearlyRate = rate.getInterestRate()
                    .divide(BigDecimal.valueOf(100));

            monthlyCosts = request.loanValue()
                    .multiply(yearlyRate)
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        }

        return new MortgageCheckResponse(feasible, monthlyCosts.doubleValue());
    }
}

