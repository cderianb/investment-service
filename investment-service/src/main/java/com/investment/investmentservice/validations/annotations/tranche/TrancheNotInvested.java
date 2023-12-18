package com.investment.investmentservice.validations.annotations.tranche;

import com.investment.investmentservice.validations.validators.tranche.TrancheIdExistsValidator;
import com.investment.investmentservice.validations.validators.tranche.TrancheNotInvestedValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TrancheNotInvestedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TrancheNotInvested {
    String message() default "Tranche is in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
