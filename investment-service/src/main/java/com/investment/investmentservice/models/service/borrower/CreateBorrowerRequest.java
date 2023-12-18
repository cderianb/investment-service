package com.investment.investmentservice.models.service.borrower;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBorrowerRequest {
    private String companyName;
    private String picName;
    @Pattern(regexp = "[0-9]{5,15}", message = "phone must numbers with length 5-15")
    private String phone;
    private String business;
    private String address;
}
