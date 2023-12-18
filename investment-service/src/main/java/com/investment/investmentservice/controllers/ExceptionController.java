package com.investment.investmentservice.controllers;

import com.investment.investmentservice.models.web.responses.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response<Object>> handleConstraintViolationException(ConstraintViolationException cve) {
        Response<Object> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .result(false)
                .errors(toErrors(cve.getConstraintViolations()))
                .build();

        return ResponseEntity.badRequest().body(response);
    }



    private Map<String, List<String>> toErrors(Set<ConstraintViolation<?>> violationSet){
        Map<String, List<String>> errors = new HashMap<>();

        violationSet.forEach(violation -> {
            errors.computeIfAbsent(violation.getPropertyPath().toString(), v -> new ArrayList<>()).add(violation.getMessage());
        });

        return errors;
    }
}
