package com.example.mortgage.config;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 *
 *  * Binds interest rate configuration from application.yml
 *  * using the prefix "interest-rates".
 *
 * @param rates
 */
@Validated
@ConfigurationProperties(prefix = "interest-rates")
public record InterestRateConfig(
        List<Rate> rates
) {
    public record Rate(
            @Positive int maturityPeriod,
            @NotNull BigDecimal interestRate
    ) {}
}


