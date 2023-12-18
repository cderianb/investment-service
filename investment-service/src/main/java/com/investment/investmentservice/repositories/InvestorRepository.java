package com.investment.investmentservice.repositories;

import com.investment.investmentservice.entities.Investor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface InvestorRepository extends R2dbcRepository<Investor, Long> {
    Mono<Boolean> existsByUsername(String username);

    Flux<Investor> findByIdIn(List<Long> ids);
}
