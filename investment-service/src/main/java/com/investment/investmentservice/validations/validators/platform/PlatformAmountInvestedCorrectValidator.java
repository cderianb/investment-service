package com.investment.investmentservice.validations.validators.platform;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.platform.PlatformAmountInvestedCorrect;
import com.investment.investmentservice.validations.datas.PlatformInvestData;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class PlatformAmountInvestedCorrectValidator implements ConstraintValidator<PlatformAmountInvestedCorrect, PlatformInvestData> {
    private final TrancheRepository trancheRepository;

    public PlatformAmountInvestedCorrectValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(PlatformInvestData data, ConstraintValidatorContext constraintValidatorContext) {
        return trancheRepository.findById(data.getTrancheId())
                .map(tranche ->
                        tranche.getMinimumInvestmentAmountPerInvestor() <= data.getAmountInvested()
                        && tranche.getMaximumInvestmentAmountPerInvestor() >= data.getAmountInvested()
                )
                .toFuture()
                .get();
    }
}
