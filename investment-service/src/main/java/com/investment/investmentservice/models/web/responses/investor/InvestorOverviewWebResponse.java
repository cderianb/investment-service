package com.investment.investmentservice.models.web.responses.investor;

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
public class InvestorOverviewWebResponse {
    private Integer totalInvestment;
    private Long totalActiveInvestment;
    private Long totalCompleteInvestment;
    private Long totalInvestedAmount;
    private BigDecimal totalEarnedInterest;
    private Long totalCompanyInvested;

    private List<Investment> investmentList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Investment{
        private Long borrowerId;
        private String companyName;
        private Long amountInvested;
        private BigDecimal monthlyInterest;
        private PlatformStatus status;
        private Long startDate;
        private Long maturityDate;
        private Long endDate;
    }
}
