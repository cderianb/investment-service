package com.investment.investmentservice.controllers;

import com.investment.investmentservice.entities.Repayment;
import com.investment.investmentservice.helpers.ResponseHelper;
import com.investment.investmentservice.models.web.responses.Response;
import com.investment.investmentservice.services.RepaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/repayment", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepaymentController {
    private final RepaymentService repaymentService;

    public RepaymentController(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }

    @PostMapping(path = "/_monthly-repayment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<List<Repayment>>> monthlyRepayment(){
        return repaymentService.monthlyRepayment()
                .map(ResponseHelper::ok);
    }
}
