package com.investment.investmentservice.validations.annotations.investor;

import com.investment.investmentservice.validations.validators.investor.InvestorUsernameUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InvestorUsernameUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvestorUsernameUnique {
    String message() default "Investor username already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
