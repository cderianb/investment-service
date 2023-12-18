package com.investment.investmentservice.models.web.requests.investor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvestorWebRequest {
    private String username;
    private String name;
    private String riskProfile;
    private String sourceOfFund;
}
