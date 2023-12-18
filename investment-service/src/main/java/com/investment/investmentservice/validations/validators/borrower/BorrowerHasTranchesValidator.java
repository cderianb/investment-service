package com.investment.investmentservice.validations.validators.borrower;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.borrower.BorrowerHasTranches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BorrowerHasTranchesValidator implements ConstraintValidator<BorrowerHasTranches, Long> {
    private final TrancheRepository trancheRepository;

    public BorrowerHasTranchesValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return !trancheRepository.existsByBorrowerId(id)
                .toFuture()
                .get();
    }
}
