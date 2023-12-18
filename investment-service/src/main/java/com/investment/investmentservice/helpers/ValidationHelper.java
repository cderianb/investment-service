package com.investment.investmentservice.helpers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationHelper {
    private final Validator validator;
    public ValidationHelper(Validator validator){
        this.validator = validator;
    }
    public void validate(Object request){
        Set<ConstraintViolation<Object>> validate = validator.validate(request);
        if (!validate.isEmpty()){
            throw new ConstraintViolationException(validate);
        }
    }
}
