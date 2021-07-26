package com.zuka.coupon.dtos;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Introspected
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {

    private String description;

}
