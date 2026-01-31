package com.example.mortgage.service;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.repository.InterestRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service responsible for:
 *  - validating mortgage feasibility
 *  - calculating monthly mortgage costs
 */
@Service
public class MortgageService {

    private static final Logger log =
            LoggerFactory.getLogger(MortgageService.class);

    private final InterestRateRepository rateRepository;

    public MortgageService(InterestRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    /**
     * Performs a mortgage feasibility check and calculates monthly costs.
     * Business rules:
     *  - Loan value must not exceed 4x annual income
     *  - Loan value must not exceed home value
     *
     * @param request mortgage check request
     * @return mortgage check response
     * @throws IllegalArgumentException if no interest rate exists for the given maturity period
     */
    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {

        log.info("Starting mortgage check for maturityPeriod={}", request.maturityPeriod());

        boolean feasible =
                request.loanValue().compareTo(
                        request.income().multiply(BigDecimal.valueOf(4))
                ) <= 0
                        && request.loanValue().compareTo(request.homeValue()) <= 0;

        log.debug("Mortgage feasibility result: {}", feasible);

        BigDecimal monthlyCosts = BigDecimal.ZERO;

        if (feasible) {
            InterestRateEntity rate = rateRepository
                    .findByMaturityPeriod(request.maturityPeriod())
                    .orElseThrow(() -> {
                        log.error("No interest rate found for maturityPeriod={}",
                                request.maturityPeriod());
                        return new IllegalArgumentException(
                                "No interest rate found for maturity period: "
                                        + request.maturityPeriod()
                        );
                    });

            // Convert percentage rate to decimal (e.g. 3.5 -> 0.035)
            BigDecimal yearlyRate = rate.getInterestRate()
                    .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

            // Monthly interest cost calculation
            monthlyCosts = request.loanValue()
                    .multiply(yearlyRate)
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

            log.debug("Calculated monthly costs={}", monthlyCosts);
        } else {
            log.info("Mortgage request not feasible based on business rules");
        }

        return new MortgageCheckResponse(feasible, monthlyCosts.doubleValue());
    }
}
