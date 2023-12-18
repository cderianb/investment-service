package com.investment.investmentservice.repositories;

import com.investment.investmentservice.entities.Repayment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface RepaymentRepository extends R2dbcRepository<Repayment, Long> {
    Flux<Repayment> findByPlatformIdIn(Set<Long> platformIds);
}
