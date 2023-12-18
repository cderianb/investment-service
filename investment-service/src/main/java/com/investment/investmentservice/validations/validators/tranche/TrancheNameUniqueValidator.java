package com.investment.investmentservice.validations.validators.tranche;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNameUnique;
import com.investment.investmentservice.validations.datas.TrancheNameUniqueData;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TrancheNameUniqueValidator implements ConstraintValidator<TrancheNameUnique, TrancheNameUniqueData> {
    private final TrancheRepository trancheRepository;

    public TrancheNameUniqueValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(TrancheNameUniqueData values, ConstraintValidatorContext context) {
        return !trancheRepository.existsByNameAndBorrowerId(values.getName(), values.getBorrowerId())
                .toFuture()
                .get();
    }
}
