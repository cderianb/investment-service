package com.investment.investmentservice.models.web.requests.platform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformInvestWebRequest {
    private Long investorId;
    private Long trancheId;
    private Long amountInvested;
}
