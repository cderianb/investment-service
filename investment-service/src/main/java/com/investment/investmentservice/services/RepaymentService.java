package com.investment.investmentservice.services;

import com.investment.investmentservice.configurations.PlatformConfiguration;
import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.entities.Repayment;
import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.repositories.RepaymentRepository;
import com.investment.investmentservice.repositories.TrancheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RepaymentService {
    private final RepaymentRepository repaymentRepository;
    private final PlatformRepository platformRepository;
    private final TrancheRepository trancheRepository;

    public RepaymentService(RepaymentRepository repaymentRepository, PlatformRepository platformRepository, TrancheRepository trancheRepository) {
        this.repaymentRepository = repaymentRepository;
        this.platformRepository = platformRepository;
        this.trancheRepository = trancheRepository;
    }

    public Mono<List<Repayment>> monthlyRepayment(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

//        return platformRepository.findByStatusAndMaturityDateGreaterThanEqual(PlatformStatus.ACTIVE, today.getTimeInMillis())
//                .filter(platform -> getMonthDifference(platform.getStartDate(), today.getTimeInMillis()) >= 1
//                                        && getDay(platform.getStartDate()).equals(getDay(today.getTimeInMillis())))
//                .collectList()
//                .flatMap(platforms -> {
//                    Set<Long> trancheIds = platforms.stream().map(Platform::getTrancheId).collect(Collectors.toSet());
//                    return trancheRepository.findAllById(trancheIds)
//                            .collectMap(Tranche::getId, Function.identity())
//                            .flatMap(trancheMap -> {
//                                List<Tuple2<Platform, Tranche>> pair = new ArrayList<>();
//                                platforms.forEach(platform -> {
//                                    Tranche tranche = trancheMap.get(platform.getTrancheId());
//                                    pair.add(Tuples.of(platform, tranche));
//                                });
//
//                                return Flux.fromIterable(pair)
//                                        .map(tuple -> {
//                                            Platform platform = tuple.getT1();
//                                            Tranche tranche = tuple.getT2();
//                                            return doRepayment(platform, tranche, today.getTimeInMillis());
//                                        }).collectList();
//                            });
//                })
//                .flatMap(this::update)
//                .map(Tuple3::getT1);

        return platformRepository.findByStatusAndMaturityDateGreaterThanEqual(PlatformStatus.ACTIVE, today.getTimeInMillis())
                .filter(platform -> isValidRepayment(platform, today.getTimeInMillis()))
                .collectList()
                .flatMapMany(this::findTranches)
                .map(platform -> doRepayment(platform, today.getTimeInMillis()))
                .collectList()
                .flatMap(this::update)
                .map(Tuple3::getT1);
    }

    private Boolean isValidRepayment(Platform platform, Long today){
        return getMonthDifference(platform.getStartDate(), today) >= 1
                && getDay(platform.getStartDate()).equals(getDay(today));
    }

    private Flux<Platform> findTranches(List<Platform> platforms){
        Set<Long> trancheIds = platforms.stream().map(Platform::getTrancheId).collect(Collectors.toSet());
        return trancheRepository.findAllById(trancheIds)
                .collectMap(Tranche::getId, Function.identity())
                .flatMapMany(trancheMap -> setTranche(platforms, trancheMap));
    }

    private Flux<Platform> setTranche(List<Platform> platforms, Map<Long, Tranche> trancheMap){
        List<Tuple2<Platform, Tranche>> pair = new ArrayList<>();
        platforms.forEach(platform -> {
            Tranche tranche = trancheMap.get(platform.getTrancheId());
            platform.setTranche(tranche);
        });
        return Flux.fromIterable(platforms);
    }

    private Tuple3<Repayment, Platform, Tranche> doRepayment(Platform platform, Long today){
        Tranche tranche = platform.getTranche();
        Repayment repayment = Repayment.builder()
                .platformId(platform.getId())
                .repaymentDate(today)
                .period(getMonthDifference(platform.getStartDate(), today))
                .interestPaid(platform.getMonthlyInterest())
                .platformFee(platform.getPlatformFee())
                .build();

        if (Objects.equals(today, platform.getMaturityDate())){
            platform.setEndDate(today);
            platform.setStatus(PlatformStatus.COMPLETE);

            Long totalAvailableInvestment = platform.getAmountInvested() + tranche.getAmountAvailableForInvestment();
            if (totalAvailableInvestment <= tranche.getMaximumInvestmentAmount()){
                tranche.setAmountAvailableForInvestment(totalAvailableInvestment);
            }
            else {
                tranche.setAmountAvailableForInvestment(tranche.getMaximumInvestmentAmount());
            }
        }

        return Tuples.of(repayment, platform, tranche);
    }

    private Integer getDay(Long date){
        LocalDate localDate = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }

    private Integer getMonthDifference(Long startDate, Long endDate){
        LocalDate localStartDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localToday = Instant.ofEpochMilli(endDate).atZone(ZoneId.systemDefault()).toLocalDate();

        int yearDifference = localToday.getYear() - localStartDate.getYear();
        return localToday.getMonthValue() - localStartDate.getMonthValue() + (yearDifference * 12);
    }

    private Mono<Tuple3<List<Repayment>, List<Platform>, List<Tranche>>> update(List<Tuple3<Repayment, Platform, Tranche>> tuple3s){
        List<Repayment> repaymentList = tuple3s.stream().map(Tuple3::getT1).collect(Collectors.toList());
        List<Platform> platformList = tuple3s.stream().map(Tuple3::getT2).collect(Collectors.toList());
        List<Tranche> trancheList = tuple3s.stream().map(Tuple3::getT3).collect(Collectors.toList());

        return Mono.zip(repaymentRepository.saveAll(repaymentList).collectList(),
                platformRepository.saveAll(platformList).collectList(),
                trancheRepository.saveAll(trancheList).collectList());
    }
}
