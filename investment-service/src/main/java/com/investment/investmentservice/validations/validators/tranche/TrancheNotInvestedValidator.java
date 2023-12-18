package com.investment.investmentservice.validations.validators.tranche;

import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNotInvested;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class TrancheNotInvestedValidator implements ConstraintValidator<TrancheNotInvested, Long> {
    private final PlatformRepository platformRepository;

    public TrancheNotInvestedValidator(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return !platformRepository.existsByTrancheId(id)
                .toFuture()
                .get();
    }
}
