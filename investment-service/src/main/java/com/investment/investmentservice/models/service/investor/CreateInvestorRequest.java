package com.investment.investmentservice.models.service.investor;

import com.investment.investmentservice.validations.annotations.investor.InvestorUsernameContainsWhitespace;
import com.investment.investmentservice.validations.annotations.investor.InvestorUsernameUnique;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvestorRequest {
    @InvestorUsernameUnique
    @InvestorUsernameContainsWhitespace
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    private String riskProfile;
    private String sourceOfFund;
}
