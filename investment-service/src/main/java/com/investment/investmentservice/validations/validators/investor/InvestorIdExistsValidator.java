package com.investment.investmentservice.validations.validators.investor;

import com.investment.investmentservice.repositories.InvestorRepository;
import com.investment.investmentservice.validations.annotations.investor.InvestorIdExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvestorIdExistsValidator implements ConstraintValidator<InvestorIdExists, Long> {
    private InvestorRepository investorRepository;

    public InvestorIdExistsValidator(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return investorRepository.existsById(id)
                .toFuture()
                .get();
    }
}
