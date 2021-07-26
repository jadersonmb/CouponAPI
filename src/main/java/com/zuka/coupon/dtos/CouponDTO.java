package com.zuka.coupon.dtos;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;

@Introspected
@Builder
@Data
public class CouponDTO {

    private String description;

}
