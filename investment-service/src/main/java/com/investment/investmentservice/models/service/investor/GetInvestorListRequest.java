package com.investment.investmentservice.models.service.investor;

import com.investment.investmentservice.models.service.PaginationRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetInvestorListRequest extends PaginationRequest {
    private String username;
    private String name;

    public String getUsername(){
        return username.trim();
    }
}
