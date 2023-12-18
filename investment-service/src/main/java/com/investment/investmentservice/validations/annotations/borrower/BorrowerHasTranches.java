package com.investment.investmentservice.validations.annotations.borrower;

import com.investment.investmentservice.validations.validators.borrower.BorrowerHasTranchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BorrowerHasTranchesValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BorrowerHasTranches {
    String message() default "Borrower still has tranche(s)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
