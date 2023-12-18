package com.investment.investmentservice.models.service.tranche;

import com.investment.investmentservice.models.service.PaginationRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetTrancheListRequest extends PaginationRequest {
    private String name;
    private String company;
}
