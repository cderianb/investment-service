package com.investment.investmentservice.validations.annotations.tranche;

import com.investment.investmentservice.validations.validators.tranche.TrancheNameUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TrancheNameUniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TrancheNameUnique {
    String message() default "Tranche name already exists for this borrower.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
