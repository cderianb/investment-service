package com.investment.investmentservice.models.service.tranche;

import com.investment.investmentservice.validations.annotations.tranche.TrancheIdExists;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNameUnique;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNotInvested;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@TrancheUpdatedNameUnique
public class UpdateTrancheRequest {
    @TrancheIdExists
    @TrancheNotInvested
    private Long id;
    @NotBlank
    private String name;
    private String annualInterestRate;
    @Min(value = 0)
    private Long amountAvailableForInvestment;
    @Min(value = 1)
    private Integer duration;
    @Min(value = 1)
    private Long maximumInvestmentAmount;
    @Min(value = 1)
    private Long minimumInvestmentAmountPerInvestor;
    @Min(value = 1)
    private Long maximumInvestmentAmountPerInvestor;

    public String getName() {
        return StringUtils.capitalize(name);
    }
}
