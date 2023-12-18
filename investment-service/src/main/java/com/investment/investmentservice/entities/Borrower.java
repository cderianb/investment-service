package com.investment.investmentservice.entities;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowers")
public class Borrower extends BaseEntity {
    @Column(value = "company_name")
    private String companyName;

    @Column(value = "pic_name")
    private String picName;

    @Column(value = "phone")
    private String phone;

    @Column(value = "business")
    private String business;

    @Column(value = "address")
    private String address;
}