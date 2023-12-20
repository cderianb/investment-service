package com.investment.investmentservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "platforms")
public class Platform extends BaseEntity{
    @Column(value = "investor_id")
    private Long investorId;

    @Column(value = "tranche_id")
    private Long trancheId;

    @Column(value = "status")
    private PlatformStatus status;

    @Column(value = "start_date")
    private Long startDate;

    @Column(value = "maturity_date")
    private Long maturityDate;

    @Column(value = "end_date")
    private Long endDate;

    @Column(value = "monthly_interest")
    private BigDecimal monthlyInterest;

    @Column(value = "amount_invested")
    private Long amountInvested;

    @Column(value = "platform_fee")
    private BigDecimal platformFee;

    @Column(value = "borrower_receive")
    private BigDecimal borrowerReceive;

    @Transient
    @JsonIgnore
    private Investor investor;

    @Transient
    @JsonIgnore
    private Tranche tranche;
}
