package com.investment.investmentservice.validations.validators.borrower;

import com.investment.investmentservice.repositories.BorrowerRepository;
import com.investment.investmentservice.validations.annotations.borrower.BorrowerIdExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BorrowerIdExistsValidator implements ConstraintValidator<BorrowerIdExists, Long> {

    private final BorrowerRepository borrowerRepository;

    public BorrowerIdExistsValidator(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return borrowerRepository.existsById(id)
                .toFuture()
                .get();
    }
}
