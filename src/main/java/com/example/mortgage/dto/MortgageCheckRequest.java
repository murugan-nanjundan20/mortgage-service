package com.example.mortgage.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public record MortgageCheckRequest(

        @NotNull(message = "Income is required")
        @Positive(message = "Income must be greater than zero")
        BigDecimal income,

        @NotNull(message = "Maturity period is required")
        @Positive(message = "Maturity period must be greater than zero")
        Integer maturityPeriod,

        @NotNull(message = "Loan value is required")
        @Positive(message = "Loan value must be greater than zero")
        BigDecimal loanValue,

        @NotNull(message = "Home value is required")
        @Positive(message = "Home value must be greater than zero")
        BigDecimal homeValue
) {
}
