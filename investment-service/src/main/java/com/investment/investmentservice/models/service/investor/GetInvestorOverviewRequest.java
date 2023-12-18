package com.investment.investmentservice.models.service.investor;

import com.investment.investmentservice.validations.annotations.investor.InvestorIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInvestorOverviewRequest {
    @InvestorIdExists
    private Long id;
}
