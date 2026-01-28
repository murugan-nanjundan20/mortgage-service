package com.example.mortgage.repository;


import com.example.mortgage.model.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRateRepository
        extends JpaRepository<InterestRate, Long> {

    Optional<InterestRate> findByMaturityPeriod(Integer maturityPeriod);
}