package com.example.mortgage.controller;


import com.example.mortgage.model.InterestRate;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import com.example.mortgage.service.InterestRateService;
import com.example.mortgage.service.MortgageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for mortgage rates and feasibility.
 */
@RestController
@RequestMapping("/api")
public class MortgageRatesController {

    @Autowired
    InterestRateService interestRateService;

    @Autowired
    MortgageService mortgageService;

    @GetMapping("/interest-rates")
    public List<InterestRate> getRates() {
        return interestRateService.interestRates();
    }

    @PostMapping("/mortgage-check")
    public MortgageCheckResponse checkMortgage(@Valid @RequestBody MortgageCheckRequest request) {
        return mortgageService.checkMortgage(request);
    }
}
