package com.investment.investmentservice.validations.validators.platform;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.platform.PlatformTrancheMaximumInvestment;
import com.investment.investmentservice.validations.datas.PlatformInvestData;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class PlatformTrancheMaximumInvestmentValidator implements ConstraintValidator<PlatformTrancheMaximumInvestment, PlatformInvestData> {
    private final TrancheRepository trancheRepository;

    public PlatformTrancheMaximumInvestmentValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(PlatformInvestData data, ConstraintValidatorContext constraintValidatorContext) {
        return trancheRepository.findById(data.getTrancheId())
                .map(tranche -> tranche.getAmountAvailableForInvestment().compareTo(data.getAmountInvested()) > 0
                                    || tranche.getAmountAvailableForInvestment().compareTo(data.getAmountInvested()) == 0)
                .defaultIfEmpty(true)
                .toFuture()
                .get();
    }
}
