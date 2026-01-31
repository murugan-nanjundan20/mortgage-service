package com.example.mortgage.config;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.repository.InterestRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Loads interest rate data from application.yml into the database
 * when the application starts.
 *
 * This runs once at startup using Spring Boot's CommandLineRunner.
 */
@Component
public class InterestRateLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(InterestRateLoader.class);

    private final InterestRateRepository repository;
    private final InterestRateConfig config;

    public InterestRateLoader(
            InterestRateRepository repository,
            InterestRateConfig config) {
        this.repository = repository;
        this.config = config;
    }

    /**
     * Reads interest rate values from YAML configuration
     * and persists them into the database.
     */
    @Override
    public void run(String... args) {
        log.debug("Loading interest rate configuration data");

        config.rates().forEach(r -> {
            log.debug("Saving interest rate: maturityPeriod={}, interestRate={}",
                    r.maturityPeriod(), r.interestRate());

            repository.save(
                    new InterestRateEntity(
                            r.maturityPeriod(),
                            r.interestRate()
                    )
            );
        });

        log.debug("Interest rate loading completed. Total records: {}", config.rates().size());
    }
}
