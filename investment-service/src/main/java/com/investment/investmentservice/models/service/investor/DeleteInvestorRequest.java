package com.investment.investmentservice.models.service.investor;

import com.investment.investmentservice.validations.annotations.investor.InvestorIdExists;
import com.investment.investmentservice.validations.annotations.investor.InvestorNotInvesting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteInvestorRequest {
    @InvestorIdExists
    @InvestorNotInvesting
    private Long id;
}
