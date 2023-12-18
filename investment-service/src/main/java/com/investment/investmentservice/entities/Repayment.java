package com.investment.investmentservice.entities;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repayments")
public class Repayment extends BaseEntity{
    @Column(value = "platform_id")
    private Long platformId;

    @Column(value = "repayment_date")
    private Long repaymentDate;

    @Column(value = "period")
    private Integer period;

    @Column(value = "interest_paid")
    private BigDecimal interestPaid;

    @Column(value = "platform_fee")
    private BigDecimal platformFee;
}
