package com.example.mortgage.service;

import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.model.MortgageCheckResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MortgageServiceTest {

    @Autowired
    MortgageService service;

    @Test
    void mortgageIsFeasible() {

        // income * 4 = 240000 >= loanValue (150000)
        MortgageCheckRequest req = new MortgageCheckRequest(
                new BigDecimal("60000"),   // yearly income
                20,
                new BigDecimal("150000"),
                new BigDecimal("200000")
        );
        MortgageCheckResponse response = service.checkMortgage(req);
        assertTrue(response.feasible());
    }


    @Test
    void mortgageNotFeasible_whenLoanExceedsFourTimesIncome() {

        // income * 4 = 80_000 < loanValue (150_000)
        MortgageCheckRequest req = new MortgageCheckRequest(
                new BigDecimal("20000"),   // yearly income
                20,
                new BigDecimal("150000"),
                new BigDecimal("200000")
        );

        MortgageCheckResponse response = service.checkMortgage(req);
        assertFalse(response.feasible());
    }

}
