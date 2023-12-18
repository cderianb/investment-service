package com.investment.investmentservice.models.service.borrower;

import com.investment.investmentservice.validations.annotations.borrower.BorrowerHasTranches;
import com.investment.investmentservice.validations.annotations.borrower.BorrowerIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBorrowerRequest {
    @BorrowerIdExists
    @BorrowerHasTranches
    private Long id;
}
