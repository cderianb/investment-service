package com.investment.investmentservice.models.service.platform;

import com.investment.investmentservice.validations.annotations.investor.InvestorIdExists;
import com.investment.investmentservice.validations.annotations.platform.PlatformAmountInvestedCorrect;
import com.investment.investmentservice.validations.annotations.platform.PlatformMaximumInvestmentPerInvestor;
import com.investment.investmentservice.validations.annotations.platform.PlatformTrancheMaximumInvestment;
import com.investment.investmentservice.validations.annotations.tranche.TrancheIdExists;
import com.investment.investmentservice.validations.datas.PlatformInvestData;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PlatformTrancheMaximumInvestment
@PlatformAmountInvestedCorrect
@PlatformMaximumInvestmentPerInvestor
public class PlatformInvestRequest implements PlatformInvestData {
    @InvestorIdExists
    private Long investorId;
    @TrancheIdExists
    private Long trancheId;
    @Min(value = 1)
    private Long amountInvested;
}
