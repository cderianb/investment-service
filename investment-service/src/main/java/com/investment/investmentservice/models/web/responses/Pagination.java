package com.investment.investmentservice.models.web.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagination{
    private Long currentPage;
    private Long totalPage;
    private Long pageSize;
    private Long totalData;
}
