package com.example.mortgage.controller;


import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.model.MortgageCheckRequest;
import com.example.mortgage.repository.InterestRateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Full integration test for MortgageRatesController
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase  // use H2 in-memory
class MortgageRatesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InterestRateRepository rateRepository;

    @BeforeEach
    void setup() {
        // Clear DB and preload test data
        rateRepository.deleteAll();

        rateRepository.save(new InterestRateEntity(12, new BigDecimal("3.5")));
        rateRepository.save(new InterestRateEntity(24, new BigDecimal("4.0")));
    }

    @Test
    void getRates_returnsInterestRatesFromDb() throws Exception {
        mockMvc.perform(get("/api/interest-rates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].maturityPeriod").value(12))
                .andExpect(jsonPath("$[0].interestRate").value(3.5))
                .andExpect(jsonPath("$[1].maturityPeriod").value(24))
                .andExpect(jsonPath("$[1].interestRate").value(4.0));
    }

    @Test
    void checkMortgage_returnsFeasibility() throws Exception {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("4000"), // income
                12,                     // maturityPeriod
                new BigDecimal("16000"), // loanValue
                new BigDecimal("250000")  // homeValue
        );

        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feasible").value(true))
                .andExpect(jsonPath("$.monthlyCosts").exists());
    }
}
