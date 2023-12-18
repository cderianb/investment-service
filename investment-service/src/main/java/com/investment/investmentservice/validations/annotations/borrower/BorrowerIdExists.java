package com.investment.investmentservice.validations.annotations.borrower;

import com.investment.investmentservice.validations.validators.borrower.BorrowerIdExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BorrowerIdExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BorrowerIdExists {
    String message() default "Borrower id doesn't exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
