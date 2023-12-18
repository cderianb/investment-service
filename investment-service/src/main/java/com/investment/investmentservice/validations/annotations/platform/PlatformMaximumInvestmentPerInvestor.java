package com.investment.investmentservice.validations.annotations.platform;

import com.investment.investmentservice.validations.validators.platform.PlatformMaximumInvestmentPerInvestorValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlatformMaximumInvestmentPerInvestorValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlatformMaximumInvestmentPerInvestor {
    String message() default "Maximum investment per investor reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
