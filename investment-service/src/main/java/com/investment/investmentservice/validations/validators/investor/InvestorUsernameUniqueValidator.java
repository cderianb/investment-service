package com.investment.investmentservice.validations.validators.investor;

import com.investment.investmentservice.repositories.InvestorRepository;
import com.investment.investmentservice.validations.annotations.investor.InvestorUsernameUnique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InvestorUsernameUniqueValidator implements ConstraintValidator<InvestorUsernameUnique, String> {

    private InvestorRepository investorRepository;

    public InvestorUsernameUniqueValidator(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !investorRepository.existsByUsername(username)
                .toFuture()
                .get();
    }
}
