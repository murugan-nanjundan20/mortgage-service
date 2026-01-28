package com.example.mortgage.service;

import com.example.mortgage.model.InterestRate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Provides interest rates.
 */
@Service
public class InterestRateService {

    public List<InterestRate> getAllRates() {
        var now = Instant.now().truncatedTo(ChronoUnit.MILLIS).toString();

        // The % is only used for display; the actual value is stored as a decimal in DB

        return List.of(
                new InterestRate(10, "10 %", now),
                new InterestRate(15, "8 %", now),
                new InterestRate(20, "7 % ", now)
        );
    }
}
