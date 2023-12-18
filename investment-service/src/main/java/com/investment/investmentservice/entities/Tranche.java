package com.investment.investmentservice.entities;

import lombok.*;
import lombok.experimental.WithBy;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tranches")
@With
public class Tranche extends BaseEntity{
    @Column(value = "name")
    private String name;

    @Column(value = "borrower_id")
    private Long borrowerId;

    @Column(value = "annual_interest_rate")
    private BigDecimal annualInterestRate;

    @Column(value = "amount_available_for_investment")
    private Long amountAvailableForInvestment;

    @Column(value = "duration")
    private Integer duration;

    @Column(value = "maximum_investment_amount")
    private Long maximumInvestmentAmount;

    @Column(value = "minimum_investment_amount_per_investor")
    private Long minimumInvestmentAmountPerInvestor;

    @Column(value = "maximum_investment_amount_per_investor")
    private Long maximumInvestmentAmountPerInvestor;

    @Transient
    private Borrower borrower;
}
