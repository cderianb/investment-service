package com.investment.investmentservice.models.web.requests;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationWebRequest {
    @Min(value = 1)
    private Long page=1L;

    @Min(value = 1)
    private Long pageSize=10L;

    private String sortBy = "last_modified_date";

    private String sortOrder = "desc";
}
