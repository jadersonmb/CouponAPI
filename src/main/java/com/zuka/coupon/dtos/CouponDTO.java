package com.zuka.coupon.dtos;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "CouponDTO")
@Introspected
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {

    @Schema(description = "Description value the coupon", minLength = 1, maxLength = 20)
    private String description;

}
