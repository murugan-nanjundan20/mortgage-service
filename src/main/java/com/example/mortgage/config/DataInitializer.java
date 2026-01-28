package com.example.mortgage.config;

import com.example.mortgage.model.InterestRate;
import com.example.mortgage.repository.InterestRateRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {

    private final InterestRateRepository repository;

    public DataInitializer(InterestRateRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadData() {
        repository.saveAll(List.of(
                new InterestRate(10, new BigDecimal("10"), LocalDateTime.now()),
                new InterestRate(20, new BigDecimal("8"), LocalDateTime.now()),
                new InterestRate(30, new BigDecimal("7"), LocalDateTime.now())
        ));
    }
}

