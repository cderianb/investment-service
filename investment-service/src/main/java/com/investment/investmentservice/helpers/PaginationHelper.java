package com.investment.investmentservice.helpers;

import com.investment.investmentservice.models.web.responses.Pagination;
import org.springframework.data.domain.Sort;

public class PaginationHelper {
    public static Pagination toPagination(Long currentPage, Long pageSize, Long totalData){
        return Pagination.builder()
                .currentPage(currentPage+1)
                .totalPage(totalData < pageSize? 1 : (long) Math.ceil((double) totalData / pageSize))
                .pageSize(pageSize)
                .totalData(totalData)
                .build();
    }

}
