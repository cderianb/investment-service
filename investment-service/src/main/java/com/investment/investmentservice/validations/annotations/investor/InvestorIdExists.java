package com.investment.investmentservice.validations.annotations.investor;

import com.investment.investmentservice.validations.validators.investor.InvestorIdExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InvestorIdExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvestorIdExists {
    String message() default "Investor id doesn't exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
