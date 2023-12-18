package com.investment.investmentservice.validations.annotations.tranche;

import com.investment.investmentservice.validations.validators.tranche.TrancheIdExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TrancheIdExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TrancheIdExists {
    String message() default "Tranche id doesn't exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
