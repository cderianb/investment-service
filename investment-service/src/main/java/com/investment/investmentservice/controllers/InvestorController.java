package com.investment.investmentservice.controllers;

import com.investment.investmentservice.entities.Investor;
import com.investment.investmentservice.helpers.ResponseHelper;
import com.investment.investmentservice.helpers.ValidationHelper;
import com.investment.investmentservice.models.service.investor.*;
import com.investment.investmentservice.models.web.requests.PaginationWebRequest;
import com.investment.investmentservice.models.web.requests.investor.CreateInvestorWebRequest;
import com.investment.investmentservice.models.web.requests.investor.UpdateInvestorWebRequest;
import com.investment.investmentservice.models.web.responses.Response;
import com.investment.investmentservice.models.web.responses.investor.InvestorOverviewWebResponse;
import com.investment.investmentservice.services.InvestorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/investor", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvestorController {

    private final ValidationHelper validationHelper;
    private final InvestorService investorService;

    public InvestorController(InvestorService investorService, ValidationHelper validationHelper) {
        this.investorService = investorService;
        this.validationHelper = validationHelper;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Investor>> postInvestor(@RequestBody CreateInvestorWebRequest request) {

        CreateInvestorRequest createInvestorRequest = CreateInvestorRequest.builder()
                .username(request.getUsername())
                .name(request.getName())
                .riskProfile(request.getRiskProfile())
                .sourceOfFund(request.getSourceOfFund())
                .build();

        validationHelper.validate(createInvestorRequest);
        return investorService.createInvestor(createInvestorRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Investor>> getInvestor(@PathVariable Long id) {
        GetInvestorRequest getInvestorRequest = GetInvestorRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(getInvestorRequest);
        return investorService.getInvestor(getInvestorRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<List<Investor>>> getInvestorList(PaginationWebRequest pagingRequest
                                        , @RequestParam(required = false) String username
                                        , @RequestParam(required = false) String name) {
        GetInvestorListRequest getInvestorListRequest = GetInvestorListRequest.builder()
                .username(username)
                .name(name)
                .page(pagingRequest.getPage()-1)
                .pageSize(pagingRequest.getPageSize())
                .sortBy(pagingRequest.getSortBy())
                .sortOrder(pagingRequest.getSortOrder())
                .build();
        return investorService.getInvestorList(getInvestorListRequest)
                .map(ResponseHelper::ok);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Investor>> updateInvestor(@PathVariable Long id, @RequestBody UpdateInvestorWebRequest request)  {
        UpdateInvestorRequest updateInvestorRequest = UpdateInvestorRequest.builder()
                .id(id)
                .name(request.getName())
                .riskProfile(request.getRiskProfile())
                .sourceOfFund(request.getSourceOfFund())
                .build();
        validationHelper.validate(updateInvestorRequest);
        return investorService.updateInvestor(updateInvestorRequest)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Long>> deleteInvestor(@PathVariable Long id) {
        DeleteInvestorRequest deleteInvestorRequest = DeleteInvestorRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(deleteInvestorRequest);
        return investorService.deleteInvestor(deleteInvestorRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<InvestorOverviewWebResponse>> getInvestorOverview(@PathVariable Long id){
        GetInvestorOverviewRequest getInvestorOverviewRequest = GetInvestorOverviewRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(getInvestorOverviewRequest);
        return investorService.getInvestorOverview(getInvestorOverviewRequest)
                .map(ResponseHelper::ok);
    }
}
