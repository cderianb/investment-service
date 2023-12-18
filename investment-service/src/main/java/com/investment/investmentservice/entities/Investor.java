package com.investment.investmentservice.entities;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "investors")
public class Investor extends BaseEntity{
    @Column(value = "username")
    private String username;

    @Column(value = "name")
    private String name;

    @Column(value = "risk_profile")
    private String riskProfile;

    @Column(value = "source_of_fund")
    private String sourceOfFund;
}
