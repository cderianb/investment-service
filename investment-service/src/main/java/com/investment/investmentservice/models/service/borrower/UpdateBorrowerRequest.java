package com.investment.investmentservice.models.service.borrower;

import com.investment.investmentservice.validations.annotations.borrower.BorrowerIdExists;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBorrowerRequest {
    @BorrowerIdExists
    private Long id;
    private String companyName;
    private String picName;
    @Pattern(regexp = "[0-9]{5,15}", message = "phone must numbers with length 5-15")
    private String phone;
    private String business;
    private String address;
}
