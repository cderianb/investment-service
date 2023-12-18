package com.investment.investmentservice.controllers;

import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.helpers.ResponseHelper;
import com.investment.investmentservice.helpers.ValidationHelper;
import com.investment.investmentservice.models.service.tranche.*;
import com.investment.investmentservice.models.web.requests.PaginationWebRequest;
import com.investment.investmentservice.models.web.requests.tranche.CreateTrancheWebRequest;
import com.investment.investmentservice.models.web.requests.tranche.UpdateTrancheWebRequest;
import com.investment.investmentservice.models.web.responses.Response;
import com.investment.investmentservice.models.web.responses.tranche.TrancheOverviewWebResponse;
import com.investment.investmentservice.services.TrancheService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/tranche", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrancheController {

    private final ValidationHelper validationHelper;
    private final TrancheService trancheService;

    public TrancheController(ValidationHelper validationHelper, TrancheService trancheService) {
        this.validationHelper = validationHelper;
        this.trancheService = trancheService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Tranche>> postTranche(@RequestBody CreateTrancheWebRequest request) {
        CreateTrancheRequest createTrancheRequest = CreateTrancheRequest.builder()
                .name(request.getName())
                .borrowerId(request.getBorrowerId())
                .annualInterestRate(request.getAnnualInterestRate())
                .duration(request.getDuration())
                .maximumInvestmentAmount(request.getMaximumInvestmentAmount())
                .minimumInvestmentAmountPerInvestor(request.getMinimumInvestmentAmountPerInvestor())
                .maximumInvestmentAmountPerInvestor(request.getMaximumInvestmentAmountPerInvestor())
                .build();

        validationHelper.validate(createTrancheRequest);
        return trancheService.createTranche(createTrancheRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Tranche>> getTranche(@PathVariable Long id) {
        GetTrancheRequest getTrancheRequest = GetTrancheRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(getTrancheRequest);
        return trancheService.getTranche(getTrancheRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<List<Tranche>>> getTrancheList(PaginationWebRequest pagingRequest
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String company) {
        GetTrancheListRequest getTrancheListRequest = GetTrancheListRequest.builder()
                .name(name)
                .company(company)
                .page(pagingRequest.getPage()-1)
                .pageSize(pagingRequest.getPageSize())
                .sortBy(pagingRequest.getSortBy())
                .sortOrder(pagingRequest.getSortOrder())
                .build();
        return trancheService.getTrancheList(getTrancheListRequest)
                .map(ResponseHelper::ok);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Tranche>> updateTranche(@PathVariable Long id, @RequestBody UpdateTrancheWebRequest request){
        UpdateTrancheRequest updateTrancheRequest = UpdateTrancheRequest.builder()
                .id(id)
                .name(request.getName())
                .annualInterestRate(request.getAnnualInterestRate())
                .duration(request.getDuration())
                .maximumInvestmentAmount(request.getMaximumInvestmentAmount())
                .minimumInvestmentAmountPerInvestor(request.getMinimumInvestmentAmountPerInvestor())
                .maximumInvestmentAmountPerInvestor(request.getMaximumInvestmentAmountPerInvestor())
                .build();

        validationHelper.validate(updateTrancheRequest);
        return trancheService.updateTranche(updateTrancheRequest)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Long>> deleteTranche(@PathVariable Long id){
        DeleteTrancheRequest deleteTrancheRequest = DeleteTrancheRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(deleteTrancheRequest);
        return trancheService.deleteTranche(deleteTrancheRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}/overview", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<TrancheOverviewWebResponse>> getTrancheInvestors(@PathVariable Long id){
        GetTrancheOverviewRequest getTrancheInvestorsRequest = GetTrancheOverviewRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(getTrancheInvestorsRequest);
        return trancheService.getTrancheOverview(getTrancheInvestorsRequest)
                .map(ResponseHelper::ok);
    }
}
