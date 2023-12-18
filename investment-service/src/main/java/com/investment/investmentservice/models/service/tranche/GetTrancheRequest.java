package com.investment.investmentservice.models.service.tranche;

import com.investment.investmentservice.validations.annotations.tranche.TrancheIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTrancheRequest {
    @TrancheIdExists
    private Long id;
}
