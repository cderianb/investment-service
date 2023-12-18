package com.investment.investmentservice.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties(prefix = "platform.configuration")
@Setter
@Getter
public class PlatformConfiguration {
    private String platformFee;
    private Integer decimalPrecision;

    public BigDecimal getPlatformFee(){
        return new BigDecimal(platformFee);
    }
}
