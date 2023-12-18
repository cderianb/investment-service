package com.investment.investmentservice.models.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class Response<T> {
    @JsonProperty("statusCode")
    protected int statusCode;

    @JsonProperty("result")
    protected Boolean result;

    @JsonProperty("data")
    protected T data;

    @JsonProperty("errors")
    protected Map<String, List<String>> errors;

    @JsonProperty("pagination")
    private Pagination pagination;

}