package com.investment.investmentservice.models.service.tranche;

import com.investment.investmentservice.validations.annotations.tranche.TrancheIdExists;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNotInvested;
import com.investment.investmentservice.validations.annotations.tranche.TrancheUpdatedNameUnique;
import com.investment.investmentservice.validations.datas.TrancheUpdatedNameUniqueData;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TrancheUpdatedNameUnique
public class UpdateTrancheRequest implements TrancheUpdatedNameUniqueData {
    @TrancheIdExists
    @TrancheNotInvested
    private Long id;
    @NotBlank
    private String name;
    private String annualInterestRate;
    @Min(value = 1)
    private Integer duration;
    @Min(value = 1)
    private Long maximumInvestmentAmount;
    @Min(value = 1)
    private Long minimumInvestmentAmountPerInvestor;
    @Min(value = 1)
    private Long maximumInvestmentAmountPerInvestor;

    public String getName() {
        return StringUtils.capitalize(name.trim());
    }
}
