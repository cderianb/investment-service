package com.investment.investmentservice.models.web.requests.borrower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBorrowerWebRequest {
    private String companyName;
    private String picName;
    private String phone;
    private String business;
    private String address;
}
