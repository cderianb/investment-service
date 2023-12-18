package com.investment.investmentservice.services;

import com.investment.investmentservice.entities.*;
import com.investment.investmentservice.helpers.PaginationHelper;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import com.investment.investmentservice.models.service.investor.*;
import com.investment.investmentservice.models.web.responses.ResponseWithPaging;
import com.investment.investmentservice.models.web.responses.investor.InvestorOverviewWebResponse;
import com.investment.investmentservice.repositories.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;
    private final PlatformRepository platformRepository;
    private final TrancheRepository trancheRepository;
    private final BorrowerRepository borrowerRepository;
    private final RepaymentRepository repaymentRepository;

    public InvestorService(InvestorRepository investorRepository, PlatformRepository platformRepository
            , TrancheRepository trancheRepository, BorrowerRepository borrowerRepository, RepaymentRepository repaymentRepository) {
        this.investorRepository = investorRepository;
        this.platformRepository = platformRepository;
        this.trancheRepository = trancheRepository;
        this.borrowerRepository = borrowerRepository;
        this.repaymentRepository = repaymentRepository;
    }

    public Mono<Investor> createInvestor(CreateInvestorRequest investorRequest) {
        return investorRepository.save(Investor.builder()
                .username(investorRequest.getUsername())
                .name(investorRequest.getName())
                .riskProfile(investorRequest.getRiskProfile())
                .sourceOfFund(investorRequest.getSourceOfFund())
                .build());
    }

    public Mono<Investor> getInvestor(GetInvestorRequest request) {
        return investorRepository.findById(request.getId());
    }

    public Mono<ResponseWithPaging<Investor>> getInvestorList(GetInvestorListRequest request) {
        Example<Investor> example = toExample(request);

        return investorRepository.findAll(example, request.getSort()).count()
                .zipWith(investorRepository.findAll(example, request.getSort())
                        .skip(request.getPage() * request.getPageSize())
                        .take(request.getPageSize())
                        .collectList())
                .map(tuple -> ResponseWithPaging.<Investor>builder()
                        .data(tuple.getT2())
                        .pagination(PaginationHelper.toPagination(request.getPage(), request.getPageSize(), tuple.getT1()))
                        .build());
    }

    public Mono<Investor> updateInvestor(UpdateInvestorRequest request){
        return investorRepository.findById(request.getId())
                .map(investor -> {
                    investor.setName(request.getName());
                    investor.setRiskProfile(request.getRiskProfile());
                    investor.setSourceOfFund(request.getSourceOfFund());
                    return investor;
                })
                .flatMap(investorRepository::save);
    }

    public Mono<Long> deleteInvestor(DeleteInvestorRequest request){
        return investorRepository.deleteById(request.getId())
                .then(Mono.fromCallable(request::getId));
    }

    public Mono<InvestorOverviewWebResponse> getInvestorOverview(GetInvestorOverviewRequest request){
        return platformRepository.findByInvestorId(request.getId())
                .collectList()
                .flatMap(this::populatePlatforms)
                .flatMap(this::getRepayments)
                .map(this::toWebResponse);
    }

    private Mono<List<Platform>> populatePlatforms(List<Platform> platforms){
        Set<Long> trancheIds = platforms.stream().map(Platform::getTrancheId).collect(Collectors.toSet());
        return trancheRepository.findAllById(trancheIds)
                .collectList()
                .flatMap(this::populateTranches)
                .map(tranches -> setTranche(tranches, platforms));
    }

    private Mono<List<Tranche>> populateTranches(List<Tranche> tranches) {
        Set<Long> borrowerIds = tranches.stream().map(Tranche::getBorrowerId).collect(Collectors.toSet());
        return borrowerRepository.findAllById(borrowerIds)
                .collectMap(Borrower::getId, Function.identity())
                .flatMap(borrowerMap -> setBorrower(tranches, borrowerMap));
    }

    private Mono<List<Tranche>> setBorrower(List<Tranche> tranches, Map<Long, Borrower> borrowerMap){
        tranches.forEach(tranche -> {
            Borrower borrower = borrowerMap.get(tranche.getBorrowerId());
            tranche.setBorrower(borrower);
        });
        return Mono.just(tranches);
    }

    private List<Platform> setTranche(List<Tranche> tranches, List<Platform> platforms){
        Map<Long, Tranche> trancheMap = tranches.stream().collect(Collectors.toMap(Tranche::getId, Function.identity()));
        platforms.forEach(platform -> {
            Tranche tranche = trancheMap.get(platform.getTrancheId());
            platform.setTranche(tranche);
        });
        return platforms;
    }

    private Mono<Tuple2<List<Platform>, List<Repayment>>> getRepayments(List<Platform> platforms){
        Set<Long> platformIds = platforms.stream().map(Platform::getId).collect(Collectors.toSet());
        return Mono.zip(Mono.just(platforms), repaymentRepository.findByPlatformIdIn(platformIds).collectList());
    }

    private InvestorOverviewWebResponse toWebResponse(Tuple2<List<Platform>, List<Repayment>> tuple2){
        List<Platform> platforms = tuple2.getT1();
        List<Repayment> repayments = tuple2.getT2();
        Long activeInvestment = platforms.stream().filter(platform -> platform.getStatus().equals(PlatformStatus.ACTIVE)).count();
        Long completeInvestment = platforms.stream().filter(platform -> platform.getStatus().equals(PlatformStatus.COMPLETE)).count();
        Long totalInvestedAmount = platforms.stream().mapToLong(Platform::getAmountInvested).sum();
        BigDecimal totalEarnedInterest = repayments.stream().map(Repayment::getInterestPaid).reduce(BigDecimal.ZERO, BigDecimal::add);
        Long totalCompanyInvested = platforms.stream().map(Platform::getTranche).map(Tranche::getBorrower).distinct().count();
        List<InvestorOverviewWebResponse.Investment> investmentList = toInvestmentList(platforms);

        return InvestorOverviewWebResponse.builder()
                .totalInvestment(platforms.size())
                .totalActiveInvestment(activeInvestment)
                .totalCompleteInvestment(completeInvestment)
                .totalInvestedAmount(totalInvestedAmount)
                .totalEarnedInterest(totalEarnedInterest)
                .totalCompanyInvested(totalCompanyInvested)
                .investmentList(investmentList)
                .build();
    }

    private List<InvestorOverviewWebResponse.Investment> toInvestmentList(List<Platform> platforms){
        return platforms.stream()
                .map(platform -> InvestorOverviewWebResponse.Investment.builder()
                        .borrowerId(platform.getTranche().getBorrowerId())
                        .companyName(platform.getTranche().getBorrower().getCompanyName())
                        .amountInvested(platform.getAmountInvested())
                        .monthlyInterest(platform.getMonthlyInterest())
                        .status(platform.getStatus())
                        .startDate(platform.getStartDate())
                        .maturityDate(platform.getMaturityDate())
                        .endDate(platform.getEndDate())
                        .build())
                .collect(Collectors.toList());
    }

    private Example<Investor> toExample(GetInvestorListRequest request){
        Investor.InvestorBuilder investorBuilder = Investor.builder()
                .name(request.getName());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        if (StringUtils.hasLength(request.getUsername())) {
            investorBuilder.username(request.getUsername());
            matcher.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        }

        return Example.of(investorBuilder.build(), matcher);
    }
}
