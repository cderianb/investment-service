package com.investment.investmentservice.models.web.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResponseWithPaging<T> {
    private List<T> data;
    private Pagination pagination;
}
