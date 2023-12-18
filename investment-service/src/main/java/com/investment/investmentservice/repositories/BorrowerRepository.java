package com.investment.investmentservice.repositories;

import com.investment.investmentservice.entities.Borrower;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BorrowerRepository extends R2dbcRepository<Borrower, Long> {
}
