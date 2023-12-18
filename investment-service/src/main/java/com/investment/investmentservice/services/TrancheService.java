package com.investment.investmentservice.services;

import com.investment.investmentservice.entities.Borrower;
import com.investment.investmentservice.entities.Investor;
import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.helpers.PaginationHelper;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import com.investment.investmentservice.models.service.tranche.*;
import com.investment.investmentservice.models.web.responses.ResponseWithPaging;
import com.investment.investmentservice.models.web.responses.tranche.TrancheOverviewWebResponse;
import com.investment.investmentservice.repositories.BorrowerRepository;
import com.investment.investmentservice.repositories.InvestorRepository;
import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.repositories.TrancheRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrancheService {
    private final TrancheRepository trancheRepository;
    private final BorrowerRepository borrowerRepository;
    private final PlatformRepository platformRepository;
    private final InvestorRepository investorRepository;

    public TrancheService(TrancheRepository trancheRepository, BorrowerRepository borrowerRepository, PlatformRepository platformRepository, InvestorRepository investorRepository) {
        this.trancheRepository = trancheRepository;
        this.borrowerRepository = borrowerRepository;
        this.platformRepository = platformRepository;
        this.investorRepository = investorRepository;
    }

    public Mono<Tranche> createTranche(CreateTrancheRequest request) {
        return trancheRepository.save(Tranche.builder()
                .name(request.getName())
                .borrowerId(request.getBorrowerId())
                .annualInterestRate(new BigDecimal(request.getAnnualInterestRate()))
                .amountAvailableForInvestment(request.getMaximumInvestmentAmount())
                .duration(request.getDuration())
                .maximumInvestmentAmount(request.getMaximumInvestmentAmount())
                .minimumInvestmentAmountPerInvestor(request.getMinimumInvestmentAmountPerInvestor())
                .maximumInvestmentAmountPerInvestor(request.getMaximumInvestmentAmountPerInvestor())
                .build());
    }

    public Mono<Tranche> getTranche(GetTrancheRequest request) {
        return trancheRepository.findById(request.getId())
                .flatMap(tranche -> borrowerRepository.findById(tranche.getBorrowerId())
                        .map(borrower -> {
                            tranche.setBorrower(borrower);
                            return tranche;
                        }));
    }

    public Mono<ResponseWithPaging<Tranche>> getTrancheList(GetTrancheListRequest request) {
        Example<Borrower> borrowerExample = toBorrowerExample(request);

        Flux<Tranche> trancheFlux = borrowerRepository.findAll(borrowerExample)
                .flatMap(borrower -> {
                    Example<Tranche> trancheExample = toTrancheExample(request, borrower.getId());
                    return trancheRepository.findAll(trancheExample, request.getSort())
                        .map(tranche -> {
                            tranche.setBorrower(borrower);
                            return tranche;
                        });
                });

        return trancheFlux.count()
                .zipWith(trancheFlux.skip(request.getPage() * request.getPageSize())
                .take(request.getPageSize())
                .collectList())
                .map(tuple -> ResponseWithPaging.<Tranche>builder()
                        .data(tuple.getT2())
                        .pagination(PaginationHelper.toPagination(request.getPage(), request.getPageSize(), tuple.getT1()))
                        .build());
    }

    public Mono<Tranche> updateTranche(UpdateTrancheRequest request){
        return trancheRepository.findById(request.getId())
                .map(tranche -> {
                    tranche.setName(request.getName());
                    tranche.setAnnualInterestRate(new BigDecimal(request.getAnnualInterestRate()));
                    tranche.setAmountAvailableForInvestment(request.getAmountAvailableForInvestment());
                    tranche.setDuration(request.getDuration());
                    tranche.setMaximumInvestmentAmount(request.getMaximumInvestmentAmount());
                    tranche.setMinimumInvestmentAmountPerInvestor(request.getMinimumInvestmentAmountPerInvestor());
                    tranche.setMaximumInvestmentAmountPerInvestor(request.getMaximumInvestmentAmountPerInvestor());
                    return tranche;
                })
                .flatMap(trancheRepository::save);
    }

    public Mono<Long> deleteTranche(DeleteTrancheRequest request){
        return trancheRepository.deleteById(request.getId())
                .then(Mono.fromCallable(request::getId));
    }

    public Mono<TrancheOverviewWebResponse> getTrancheOverview(GetTrancheOverviewRequest request){
        return getPlatformList(request)
                .flatMap(this::findInvestors)
                .map(this::toTrancheOverviewWebResponse);
    }

    private Mono<List<Platform>> getPlatformList(GetTrancheOverviewRequest request){
        return platformRepository.findByTrancheId(request.getId())
                .collectList();
    }

    private Mono<List<Platform>> findInvestors(List<Platform> platforms){
        Set<Long> investorIds = platforms.stream()
                .map(Platform::getInvestorId)
                .collect(Collectors.toSet());

        return investorRepository.findAllById(investorIds)
                .collectMap(Investor::getId, Function.identity())
                .flatMap(investorMap -> setInvestor(platforms, investorMap));
    }

    private Mono<List<Platform>> setInvestor(List<Platform> platforms, Map<Long, Investor> investorMap){
        platforms.forEach(platform -> {
            Investor investor = investorMap.get(platform.getInvestorId());
            platform.setInvestor(investor);
        });
        return Mono.just(platforms);
    }

    private TrancheOverviewWebResponse toTrancheOverviewWebResponse(List<Platform> platformList){
        Long totalInvestors = platformList.stream().map(Platform::getInvestorId).distinct().count();
        Long activeInvestments = platformList.stream().map(Platform::getStatus)
                .filter(status -> status.equals(PlatformStatus.ACTIVE))
                .count();
        Long completeInvestments = platformList.stream().map(Platform::getStatus)
                .filter(status -> status.equals(PlatformStatus.COMPLETE))
                .count();
        Long investedAmount = platformList.stream().mapToLong(Platform::getAmountInvested)
                .sum();
        BigDecimal totalReceived = platformList.stream().map(Platform::getBorrowerReceive).reduce(BigDecimal.ZERO, BigDecimal::add);
        List<TrancheOverviewWebResponse.TrancheInvestor> investorList = platformList.stream()
                .map(platform -> {
                    return TrancheOverviewWebResponse.TrancheInvestor.builder()
                            .investorId(platform.getInvestorId())
                            .investorName(platform.getInvestor().getName())
                            .amountInvested(platform.getAmountInvested())
                            .platformStatus(platform.getStatus())
                            .build();
                })
                .sorted((p1, p2) -> p1.getPlatformStatus().compareTo(p2.getPlatformStatus()))
                .collect(Collectors.toList());

        return TrancheOverviewWebResponse.builder()
                .totalInvestors(totalInvestors)
                .activeInvestments(activeInvestments)
                .completeInvestments(completeInvestments)
                .totalInvestedAmount(investedAmount)
                .totalReceivedAmount(totalReceived)
                .investors(investorList)
                .build();
    }

    private Example<Tranche> toTrancheExample(GetTrancheListRequest request, Long borrowerId){
        Tranche.TrancheBuilder trancheBuilder = Tranche.builder()
                .name(request.getName())
                .borrowerId(borrowerId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return Example.of(trancheBuilder.build(), matcher);
    }

    private Example<Borrower> toBorrowerExample(GetTrancheListRequest request){
        Borrower.BorrowerBuilder borrowerBuilder = Borrower.builder()
                .companyName(request.getCompany());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("companyName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return Example.of(borrowerBuilder.build(), matcher);
    }
}
