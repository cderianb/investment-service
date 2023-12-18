package com.investment.investmentservice.validations.annotations.platform;

import com.investment.investmentservice.validations.validators.platform.PlatformAmountInvestedCorrectValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlatformAmountInvestedCorrectValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlatformAmountInvestedCorrect {
    String message() default "Amount invested must in tranche allowed investment range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
