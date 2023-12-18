package com.investment.investmentservice.validations.validators.investor;

import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.validations.annotations.investor.InvestorNotInvesting;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class InvestorNotInvestingValidator implements ConstraintValidator<InvestorNotInvesting, Long> {
    private final PlatformRepository platformRepository;

    public InvestorNotInvestingValidator(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return !platformRepository.existsByInvestorId(id)
                .toFuture()
                .get();
    }
}
