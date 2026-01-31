package com.example.mortgage.repository;

import com.example.mortgage.entity.InterestRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing interest rate data.
 * Uses Spring Data JPA to provide CRUD operations
 * and custom queries for {@link InterestRateEntity}.
 */
public interface InterestRateRepository
        extends JpaRepository<InterestRateEntity, Long> {

    /**
     * Finds an interest rate by its maturity period.
     *
     * @param maturityPeriod the loan maturity period
     * @return optional containing the interest rate entity if found
     */
    Optional<InterestRateEntity> findByMaturityPeriod(Integer maturityPeriod);
}
