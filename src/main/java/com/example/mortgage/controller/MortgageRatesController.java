package com.example.mortgage.controller;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.service.InterestRateService;
import com.example.mortgage.service.MortgageService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing APIs for:
 * - retrieving available mortgage interest rates
 * - performing mortgage feasibility checks
 */
@RestController
@RequestMapping("/api")
public class MortgageRatesController {

    private static final Logger log = LoggerFactory.getLogger(MortgageRatesController.class);

    private final InterestRateService interestRateService;
    private final MortgageService mortgageService;

    public MortgageRatesController(
            InterestRateService interestRateService,
            MortgageService mortgageService) {
        this.interestRateService = interestRateService;
        this.mortgageService = mortgageService;
    }

    /**
     * Returns all available interest rates.
     *
     * @return list of interest rate entities
     */
    @GetMapping("/interest-rates")
    public List<InterestRateEntity> getRates() {
        log.debug("Received request to fetch interest rates");

        List<InterestRateEntity> rates = interestRateService.interestRates();

        log.debug("Returning {} interest rate records", rates.size());
        return rates;
    }

    /**
     * Performs a mortgage feasibility check based on the request payload.
     *
     * @param request validated mortgage check request
     * @return mortgage feasibility response
     */
    @PostMapping("/mortgage-check")
    public MortgageCheckResponse checkMortgage(
            @Valid @RequestBody MortgageCheckRequest request) {

        log.debug("Received mortgage check request");

        MortgageCheckResponse response = mortgageService.checkMortgage(request);

        log.debug("Mortgage check completed: feasible={},monthlyCosts= {}", response.feasible(), response.monthlyCosts());
        return response;
    }
}
