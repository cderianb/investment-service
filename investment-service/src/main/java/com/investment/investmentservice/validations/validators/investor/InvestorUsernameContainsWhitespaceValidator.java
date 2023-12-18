package com.investment.investmentservice.validations.validators.investor;

import com.investment.investmentservice.validations.annotations.investor.InvestorUsernameContainsWhitespace;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class InvestorUsernameContainsWhitespaceValidator implements ConstraintValidator<InvestorUsernameContainsWhitespace, String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasLength(username)){
            return true;
        }
        return !username.contains(" ");
    }
}
