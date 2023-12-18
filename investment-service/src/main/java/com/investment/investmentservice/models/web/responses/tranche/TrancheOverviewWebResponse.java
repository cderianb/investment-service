package com.investment.investmentservice.models.web.responses.tranche;

import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrancheOverviewWebResponse {
    private Long totalInvestors;
    private Long activeInvestments;
    private Long completeInvestments;
    private Long totalInvestedAmount;
    private BigDecimal totalReceivedAmount;
    private List<TrancheInvestor> investors;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrancheInvestor{
        private Long investorId;
        private String investorName;
        private Long amountInvested;
        private PlatformStatus platformStatus;
    }

}
