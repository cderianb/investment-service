package com.investment.investmentservice.validations.annotations.platform;

import com.investment.investmentservice.validations.validators.platform.PlatformTrancheMaximumInvestmentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlatformTrancheMaximumInvestmentValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlatformTrancheMaximumInvestment {
    String message() default "Tranche maximum investment reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
