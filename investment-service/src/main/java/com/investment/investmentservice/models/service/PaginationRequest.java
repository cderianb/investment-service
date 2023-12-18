package com.investment.investmentservice.models.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class PaginationRequest {
    private Long page;
    private Long pageSize;
    private String sortBy;
    private String sortOrder;

    public Sort getSort(){
        return Sort.by(Sort.Direction.fromString(this.getSortOrder()), this.getSortBy());
    }
}
