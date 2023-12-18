package com.investment.investmentservice.services;

import com.investment.investmentservice.entities.Borrower;
import com.investment.investmentservice.helpers.PaginationHelper;
import com.investment.investmentservice.models.service.borrower.*;
import com.investment.investmentservice.models.web.responses.ResponseWithPaging;
import com.investment.investmentservice.repositories.BorrowerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Mono<Borrower> createBorrower(CreateBorrowerRequest request){
        return borrowerRepository.save(Borrower.builder()
                .companyName(request.getCompanyName())
                .picName(request.getPicName())
                .phone(request.getPhone())
                .business(request.getBusiness())
                .address(request.getAddress())
                .build());
    }

    public Mono<Borrower> getBorrower(GetBorrowerRequest request){
        return borrowerRepository.findById(request.getId());
    }

    public Mono<ResponseWithPaging<Borrower>> getBorrowerList(GetBorrowerListRequest request) {
        Example<Borrower> example = toExample(request);

        return borrowerRepository.findAll(example, request.getSort()).count()
                .zipWith(borrowerRepository.findAll(example, request.getSort())
                        .skip(request.getPage() * request.getPageSize())
                        .take(request.getPageSize())
                        .collectList())
                .map(tuple -> ResponseWithPaging.<Borrower>builder()
                        .data(tuple.getT2())
                        .pagination(PaginationHelper.toPagination(request.getPage(), request.getPageSize(), tuple.getT1()))
                        .build());
    }

    public Mono<Borrower> updateBorrower(UpdateBorrowerRequest request){
        return borrowerRepository.findById(request.getId())
                .map(borrower -> {
                    borrower.setCompanyName(request.getCompanyName());
                    borrower.setPicName(request.getPicName());
                    borrower.setPhone(request.getPhone());
                    borrower.setBusiness(request.getBusiness());
                    borrower.setAddress(request.getAddress());
                    return borrower;
                })
                .flatMap(borrowerRepository::save);
    }

    public Mono<Long> deleteBorrower(DeleteBorrowerRequest request){
        return borrowerRepository.deleteById(request.getId())
                .then(Mono.fromCallable(request::getId));
    }

    private Example<Borrower> toExample(GetBorrowerListRequest request){
        Borrower borrower = Borrower.builder()
                .companyName(request.getCompanyName())
                .picName(request.getPicName())
                .business(request.getBusiness())
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("companyName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("picName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("business", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return Example.of(borrower, matcher);
    }
}
