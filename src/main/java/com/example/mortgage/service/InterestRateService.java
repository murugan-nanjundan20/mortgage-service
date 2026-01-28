package com.example.mortgage.service;

import com.example.mortgage.model.InterestRate;
import com.example.mortgage.repository.InterestRateRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Provides interest rates.
 */
@Service
public class InterestRateService {

    private final InterestRateRepository repository;

    public InterestRateService(InterestRateRepository repository) {
        this.repository = repository;
    }
    public List<InterestRate> interestRates() {
        return repository.findAll();
    }


}
