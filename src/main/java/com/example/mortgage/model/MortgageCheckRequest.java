package com.example.mortgage.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public record MortgageCheckRequest(

        @NotNull @Positive
        BigDecimal income,

        @NotNull @Positive
        Integer maturityPeriod,

        @NotNull @Positive
        BigDecimal loanValue,

        @NotNull @Positive
        BigDecimal homeValue
) {
}
