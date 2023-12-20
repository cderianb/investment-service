package com.investment.investmentservice.models.service.platform;

import com.investment.investmentservice.validations.annotations.platform.PlatformIdExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlatformRequest {
    @PlatformIdExists
    private Long id;
}
