package com.investment.investmentservice.models.service.borrower;

import com.investment.investmentservice.models.service.PaginationRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowerListRequest extends PaginationRequest {
    private String companyName;
    private String picName;
    private String business;
}
