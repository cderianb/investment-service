package com.investment.investmentservice.models.service.tranche;

import com.investment.investmentservice.validations.annotations.borrower.BorrowerIdExists;
import com.investment.investmentservice.validations.annotations.tranche.TrancheNameUnique;
import com.investment.investmentservice.validations.datas.TrancheNameUniqueData;
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
@TrancheNameUnique
public class CreateTrancheRequest implements TrancheNameUniqueData {
    @NotBlank
    private String name;
    @BorrowerIdExists
    private Long borrowerId;
    @Min(value = 0)
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
        return StringUtils.capitalize(name);
    }
}
