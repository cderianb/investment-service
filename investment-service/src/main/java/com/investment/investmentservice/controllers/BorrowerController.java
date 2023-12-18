package com.investment.investmentservice.controllers;

import com.investment.investmentservice.entities.Borrower;
import com.investment.investmentservice.helpers.ResponseHelper;
import com.investment.investmentservice.helpers.ValidationHelper;
import com.investment.investmentservice.models.service.borrower.*;
import com.investment.investmentservice.models.web.requests.PaginationWebRequest;
import com.investment.investmentservice.models.web.requests.borrower.CreateBorrowerWebRequest;
import com.investment.investmentservice.models.web.requests.borrower.UpdateBorrowerWebRequest;
import com.investment.investmentservice.models.web.responses.Response;
import com.investment.investmentservice.services.BorrowerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/borrower", produces = MediaType.APPLICATION_JSON_VALUE)
public class BorrowerController {
    private final ValidationHelper validationHelper;
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService, ValidationHelper validationHelper) {
        this.borrowerService = borrowerService;
        this.validationHelper = validationHelper;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Borrower>> postInvestor(@RequestBody CreateBorrowerWebRequest request) {
        CreateBorrowerRequest createBorrowerRequest = CreateBorrowerRequest.builder()
                .companyName(request.getCompanyName())
                .picName(request.getPicName())
                .phone(request.getPhone())
                .business(request.getBusiness())
                .address(request.getAddress())
                .build();

        validationHelper.validate(createBorrowerRequest);
        return borrowerService.createBorrower(createBorrowerRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Borrower>> getBorrower(@PathVariable Long id){
        GetBorrowerRequest getBorrowerRequest = GetBorrowerRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(getBorrowerRequest);
        return borrowerService.getBorrower(getBorrowerRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<List<Borrower>>> getBorrowerList(PaginationWebRequest pagingRequest
            , @RequestParam(required = false) String companyName
            , @RequestParam(required = false) String picName
            , @RequestParam(required = false) String business) {
        GetBorrowerListRequest getBorrowerListRequest = GetBorrowerListRequest.builder()
                .companyName(companyName)
                .picName(picName)
                .business(business)
                .page(pagingRequest.getPage()-1)
                .pageSize(pagingRequest.getPageSize())
                .sortBy(pagingRequest.getSortBy())
                .sortOrder(pagingRequest.getSortOrder())
                .build();
        return borrowerService.getBorrowerList(getBorrowerListRequest)
                .map(ResponseHelper::ok);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Borrower>> updateBorrower(@PathVariable Long id, @RequestBody UpdateBorrowerWebRequest request){
        UpdateBorrowerRequest updateBorrowerRequest = UpdateBorrowerRequest.builder()
                .id(id)
                .companyName(request.getCompanyName())
                .picName(request.getPicName())
                .phone(request.getPhone())
                .business(request.getBusiness())
                .address(request.getAddress())
                .build();
        validationHelper.validate(updateBorrowerRequest);
        return borrowerService.updateBorrower(updateBorrowerRequest)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Long>> deleteBorrower(@PathVariable Long id) {
        DeleteBorrowerRequest deleteBorrowerRequest = DeleteBorrowerRequest.builder()
                .id(id)
                .build();
        validationHelper.validate(deleteBorrowerRequest);
        return borrowerService.deleteBorrower(deleteBorrowerRequest)
                .map(ResponseHelper::ok);
    }
}
