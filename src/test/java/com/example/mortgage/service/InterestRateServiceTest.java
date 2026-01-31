package com.example.mortgage.service;

import com.example.mortgage.entity.InterestRateEntity;
import com.example.mortgage.repository.InterestRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link InterestRateService}.
 */
@ExtendWith(MockitoExtension.class)
class InterestRateServiceTest {

    @Mock
    private InterestRateRepository repository;

    @InjectMocks
    private InterestRateService service;

    private InterestRateEntity rateEntity;

    @BeforeEach
    void setUp() {
        rateEntity = new InterestRateEntity(
                12,
                new BigDecimal("3.5")
        );
    }

    /**
     * Should return interest rates when repository contains data.
     */
    @Test
    void interestRates_returnsRates_whenRatesExist() {

        when(repository.findAll()).thenReturn(List.of(rateEntity));

        List<InterestRateEntity> result = service.interestRates();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(rateEntity, result.get(0));

        verify(repository, times(1)).findAll();
    }

    /**
     * Should throw IllegalStateException when no interest rates exist.
     */
    @Test
    void interestRates_throwsException_whenNoRatesExist() {

        when(repository.findAll()).thenReturn(List.of());
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.interestRates()
        );

        assertEquals("No interest rates configured", exception.getMessage());
        verify(repository, times(1)).findAll();
    }
}
