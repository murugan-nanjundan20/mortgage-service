# Mortgage Backend Service

Spring Boot REST API for mortgage feasibility checks.

## Endpoints

### GET {env}/api/interest-rates
Returns available mortgage interest rates.


### POST {env}/api/mortgage-check
Checks whether a mortgage is feasible and returns monthly costs.

### Sample payload: 

    {
    "income": 4000,
    "maturityPeriod": 10,
    "loanValue": 5000,
    "homeValue": 4500
    }


## Business Rules
- Loan ≤ 4 × income
- Loan ≤ home value

## Tech Stack
- Java 21
- Spring Boot 4.2
- Maven
