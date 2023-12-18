package com.investment.investmentservice.validations.annotations.investor;

import com.investment.investmentservice.validations.validators.investor.InvestorNotInvestingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InvestorNotInvestingValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvestorNotInvesting {
    String message() default "Investor has investment";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
