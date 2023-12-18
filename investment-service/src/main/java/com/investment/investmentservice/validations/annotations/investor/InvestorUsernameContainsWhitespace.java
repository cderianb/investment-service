package com.investment.investmentservice.validations.annotations.investor;

import com.investment.investmentservice.validations.validators.investor.InvestorUsernameContainsWhitespaceValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InvestorUsernameContainsWhitespaceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvestorUsernameContainsWhitespace {
    String message() default "Investor username contains whitespace";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
