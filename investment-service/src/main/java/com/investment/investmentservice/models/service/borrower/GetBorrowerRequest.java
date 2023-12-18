package com.investment.investmentservice.models.service.borrower;

import com.investment.investmentservice.validations.annotations.borrower.BorrowerIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowerRequest {
    @BorrowerIdExists
    private Long id;
}
