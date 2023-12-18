package com.investment.investmentservice.helpers;

import com.investment.investmentservice.models.web.responses.ResponseWithPaging;
import com.investment.investmentservice.models.web.responses.Response;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseHelper {
    public static<T> Response<T> ok(T data){
        return Response.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .result(true)
                .data(data)
                .build();
    }

    public static<T> Response<List<T>> ok(ResponseWithPaging<T> data){
        return Response.<List<T>>builder()
                .statusCode(HttpStatus.OK.value())
                .result(true)
                .data(data.getData())
                .pagination(data.getPagination())
                .build();
    }
}
