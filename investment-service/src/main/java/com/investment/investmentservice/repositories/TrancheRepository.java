package com.investment.investmentservice.repositories;

import com.investment.investmentservice.entities.Tranche;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrancheRepository extends R2dbcRepository<Tranche, Long> {
    Mono<Boolean> existsByNameAndBorrowerId(String name, Long borrowerId);

    Mono<Boolean> existsByBorrowerId(Long borrowerId);
}
