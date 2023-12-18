package com.investment.investmentservice.models.web.requests.tranche;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTrancheWebRequest {
    private String name;
    private String annualInterestRate;
    private Integer duration;
    private Long maximumInvestmentAmount;
    private Long minimumInvestmentAmountPerInvestor;
    private Long maximumInvestmentAmountPerInvestor;
}
