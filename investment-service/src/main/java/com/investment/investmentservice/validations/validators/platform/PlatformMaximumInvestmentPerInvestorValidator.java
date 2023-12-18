package com.investment.investmentservice.validations.validators.platform;

import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.platform.PlatformMaximumInvestmentPerInvestor;
import com.investment.investmentservice.validations.datas.PlatformInvestData;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

public class PlatformMaximumInvestmentPerInvestorValidator implements ConstraintValidator<PlatformMaximumInvestmentPerInvestor, PlatformInvestData> {

    private final TrancheRepository trancheRepository;
    private final PlatformRepository platformRepository;

    public PlatformMaximumInvestmentPerInvestorValidator(TrancheRepository trancheRepository, PlatformRepository platformRepository) {
        this.trancheRepository = trancheRepository;
        this.platformRepository = platformRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(PlatformInvestData data, ConstraintValidatorContext constraintValidatorContext) {
        return Mono.zip(trancheRepository.findById(data.getTrancheId()),
                        platformRepository.findByTrancheIdAndInvestorId(data.getTrancheId(), data.getInvestorId()).collectList())
                .map(tuple2 -> validateInvestedAmount(tuple2, data.getAmountInvested()))
                .defaultIfEmpty(true)
                .toFuture()
                .get();
    }

    private boolean validateInvestedAmount(Tuple2<Tranche, List<Platform>> tuple2, Long requestAmountInvested){
        Tranche tranche = tuple2.getT1();
        List<Platform> platforms = tuple2.getT2();
        Long totalInvested = platforms.stream().mapToLong(Platform::getAmountInvested).sum();

        return tranche.getMaximumInvestmentAmountPerInvestor() >= requestAmountInvested + totalInvested;
    }
}
