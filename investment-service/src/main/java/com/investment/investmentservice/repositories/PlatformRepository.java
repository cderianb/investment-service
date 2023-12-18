package com.investment.investmentservice.repositories;

import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlatformRepository extends R2dbcRepository<Platform, Long> {
    Flux<Platform> findByTrancheIdAndInvestorId(Long trancheId, Long investorId);

    Mono<Boolean> existsByInvestorId(Long investorId);

    Mono<Boolean> existsByTrancheId(Long trancheId);

    Flux<Platform> findByStatusAndMaturityDateGreaterThanEqual(PlatformStatus status, Long today);

    Flux<Platform> findByTrancheId(Long trancheId);

    Flux<Platform> findByInvestorId(Long investorId);
}
