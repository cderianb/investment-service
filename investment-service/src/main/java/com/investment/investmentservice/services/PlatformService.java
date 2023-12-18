package com.investment.investmentservice.services;

import com.investment.investmentservice.configurations.PlatformConfiguration;
import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.entities.Tranche;
import com.investment.investmentservice.models.commons.enums.PlatformStatus;
import com.investment.investmentservice.models.service.platform.PlatformInvestRequest;
import com.investment.investmentservice.repositories.InvestorRepository;
import com.investment.investmentservice.repositories.PlatformRepository;
import com.investment.investmentservice.repositories.TrancheRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;

@Slf4j
@Service
@AllArgsConstructor
public class PlatformService {
    private final PlatformRepository platformRepository;
    private final TrancheRepository trancheRepository;
    private final InvestorRepository investorRepository;

    private final PlatformConfiguration platformConfiguration;

    public Mono<Platform> invest(PlatformInvestRequest request){
        return trancheRepository.findById(request.getTrancheId())
                .flatMap(tranche -> updateTranche(request, tranche))
                .map(tranche -> constructPlatform(request, tranche))
                .flatMap(platformRepository::save);
    }

    private Mono<Tranche> updateTranche(PlatformInvestRequest request, Tranche tranche){
        tranche.setAmountAvailableForInvestment(tranche.getAmountAvailableForInvestment() - request.getAmountInvested());
        return trancheRepository.save(tranche);
    }

    private Platform constructPlatform(PlatformInvestRequest request, Tranche tranche){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        Calendar maturity = Calendar.getInstance();
        maturity.set(Calendar.HOUR_OF_DAY, 0);
        maturity.set(Calendar.MINUTE, 0);
        maturity.set(Calendar.SECOND, 0);
        maturity.set(Calendar.MILLISECOND, 0);
        maturity.add(Calendar.MONTH, tranche.getDuration());

        MathContext precision = new MathContext(platformConfiguration.getDecimalPrecision());
        BigDecimal bd_AmountInvested = BigDecimal.valueOf(request.getAmountInvested());

        BigDecimal monthlyInterest = (bd_AmountInvested.multiply(tranche.getAnnualInterestRate()))
                                        .divide(BigDecimal.valueOf(12), precision);

        BigDecimal platformFee = bd_AmountInvested.multiply(platformConfiguration.getPlatformFee(), precision);

        BigDecimal borrowerReceive = bd_AmountInvested.subtract(platformFee, precision);

        return Platform.builder()
                .investorId(request.getInvestorId())
                .trancheId(request.getTrancheId())
                .status(PlatformStatus.ACTIVE)
                .startDate(today.getTimeInMillis())
                .maturityDate(maturity.getTimeInMillis())
                .endDate(null)
                .amountInvested(request.getAmountInvested())
                .platformFee(platformFee)
                .borrowerReceive(borrowerReceive)
                .monthlyInterest(monthlyInterest)
                .build();
    }
}

