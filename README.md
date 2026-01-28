# Mortgage Backend Service

Spring Boot REST API for mortgage feasibility checks.

## Endpoints

### GET  /api/interest-rates
Returns available mortgage interest rates.


### POST  /api/mortgage-check
Checks whether a mortgage is feasible and returns monthly costs.

### Sample payload: 

    {
    "income": 4000,
    "maturityPeriod": 10,
    "loanValue": 5000,
    "homeValue": 4500
    }


### Access H2 Database : (optional):

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:mortgagedb
User: sa
Password: (leave empty)


## Business Rules
- Loan ≤ 4 × income
- Loan ≤ home value

## Tech Stack
- Java 21
- Spring Boot 4.2
- Spring Web (REST)
- Spring Data JPA
- H2 in-memory database
- Jakarta Validation (for DTO validation)
- JUnit 5 (unit tests)
