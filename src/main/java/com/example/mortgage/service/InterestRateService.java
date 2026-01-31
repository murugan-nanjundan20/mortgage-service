package com.example.mortgage.service;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.exception.InterestRateNotFoundException;
import com.example.mortgage.repository.InterestRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsible for providing interest rate data.
 *
 */
@Service
public class InterestRateService {

    private static final Logger log =
            LoggerFactory.getLogger(InterestRateService.class);

    private final InterestRateRepository repository;

    public InterestRateService(InterestRateRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all available interest rates.
     *
     * @return list of interest rate entities
     * @throws IllegalStateException if no interest rates are configured
     */
    public List<InterestRateEntity> interestRates() {
        List<InterestRateEntity> rates = repository.findAll();

        if (rates.isEmpty()) {
            log.error("No interest rates found in the database");
            throw new InterestRateNotFoundException("No interest rates configured");
        }

        log.debug("Found {} interest rate records", rates.size());
        return rates;
    }
}
