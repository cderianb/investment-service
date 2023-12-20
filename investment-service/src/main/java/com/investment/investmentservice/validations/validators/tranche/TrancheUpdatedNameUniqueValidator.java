package com.investment.investmentservice.validations.validators.tranche;

import com.investment.investmentservice.repositories.TrancheRepository;
import com.investment.investmentservice.validations.annotations.tranche.TrancheUpdatedNameUnique;
import com.investment.investmentservice.validations.datas.TrancheUpdatedNameUniqueData;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class TrancheUpdatedNameUniqueValidator implements ConstraintValidator<TrancheUpdatedNameUnique, TrancheUpdatedNameUniqueData> {
    private final TrancheRepository trancheRepository;

    public TrancheUpdatedNameUniqueValidator(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    @Override
    @SneakyThrows
    public boolean isValid(TrancheUpdatedNameUniqueData data, ConstraintValidatorContext constraintValidatorContext) {
        return !trancheRepository.findById(data.getId())
                .filter(Objects::nonNull)
                .flatMap(tranche -> {
                    if(tranche.getName().equals(data.getName())){
                        return Mono.just(false);
                    }
                    return trancheRepository.existsByNameAndBorrowerId(data.getName(), tranche.getBorrowerId());
                })
                .defaultIfEmpty(false)
                .toFuture()
                .get();
    }
}
