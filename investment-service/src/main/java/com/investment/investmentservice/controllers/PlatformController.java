package com.investment.investmentservice.controllers;

import com.investment.investmentservice.entities.Platform;
import com.investment.investmentservice.helpers.ResponseHelper;
import com.investment.investmentservice.helpers.ValidationHelper;
import com.investment.investmentservice.models.service.platform.GetPlatformRequest;
import com.investment.investmentservice.models.service.platform.PlatformInvestRequest;
import com.investment.investmentservice.models.web.requests.platform.PlatformInvestWebRequest;
import com.investment.investmentservice.models.web.responses.Response;
import com.investment.investmentservice.services.PlatformService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/platform", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlatformController {

    private final ValidationHelper validationHelper;
    private final PlatformService platformService;

    public PlatformController(ValidationHelper validationHelper, PlatformService platformService) {
        this.validationHelper = validationHelper;
        this.platformService = platformService;
    }

    @PostMapping(path = "/_invest", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Platform>> invest(@RequestBody PlatformInvestWebRequest request){
        PlatformInvestRequest platformInvestRequest = PlatformInvestRequest.builder()
                .investorId(request.getInvestorId())
                .trancheId(request.getTrancheId())
                .amountInvested(request.getAmountInvested())
                .build();
        validationHelper.validate(platformInvestRequest);
        return platformService.invest(platformInvestRequest)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<Platform>> getPlatform(@PathVariable Long id){
        GetPlatformRequest getPlatformRequest = GetPlatformRequest.builder()
                .id(id)
                .build();
        return platformService.getPlatform(getPlatformRequest)
                .map(ResponseHelper::ok);
    }
}
