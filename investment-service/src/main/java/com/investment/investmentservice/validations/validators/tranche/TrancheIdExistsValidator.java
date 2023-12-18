package com.investment.investmentservice.validations.validators.tranche;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.tranche.TrancheIdExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrancheIdExistsValidator implements ConstraintValidator<TrancheIdExists, Long> {

    private final TrancheRepository trancheRepository;

    public TrancheIdExistsValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return trancheRepository.existsById(id)
                .toFuture()
                .get();
    }
}
