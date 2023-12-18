package com.investment.investmentservice.validations.validators.platform;

import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.validations.annotations.platform.PlatformIdExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class PlatformIdExistsValidator implements ConstraintValidator<PlatformIdExists, Long> {
    private final PlatformRepository platformRepository;

    public PlatformIdExistsValidator(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return platformRepository.existsById(id)
                .toFuture()
                .get();
    }
}
