package com.example.mortgage.controller;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.dto.MortgageCheckRequest;
import com.example.mortgage.dto.MortgageCheckResponse;
import com.example.mortgage.service.InterestRateService;
import com.example.mortgage.service.MortgageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link MortgageRatesController} using MockMvc.
 */
@WebMvcTest(MortgageRatesController.class)
class MortgageRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InterestRateService interestRateService;

    @MockBean
    private MortgageService mortgageService;

    /**
     * Test GET /api/interest-rates returns interest rates list
     */
    @Test
    void getRates_returnsInterestRates() throws Exception {
        InterestRateEntity rate = new InterestRateEntity(12, new BigDecimal("3.5"));

        when(interestRateService.interestRates()).thenReturn(List.of(rate));

        mockMvc.perform(get("/api/interest-rates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].maturityPeriod").value(12))
                .andExpect(jsonPath("$[0].interestRate").value(3.5));
    }

    /**
     * Test POST /api/mortgage-check returns mortgage check response
     */
    @Test
    void checkMortgage_returnsResponse() throws Exception {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("4000"),  // income
                12,                       // maturityPeriod
                new BigDecimal("200000"), // loanValue
                new BigDecimal("250000")  // homeValue
        );

        MortgageCheckResponse response = new MortgageCheckResponse(true, 583.33);

        when(mortgageService.checkMortgage(any())).thenReturn(response);

        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feasible").value(true))
                .andExpect(jsonPath("$.monthlyCosts").value(583.33));
    }

    /**
     * Test POST /api/mortgage-check with invalid input triggers validation error
     */
    @Test
    void checkMortgage_returnsBadRequest_whenInvalidInput() throws Exception {
        // Empty JSON → fails @Valid
        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
