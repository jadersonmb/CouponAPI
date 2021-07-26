package com.zuka.coupon.mappers;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.models.Coupon;

public class CouponMapper {

    public static CouponDTO toDTO(Coupon coupon){
        return CouponDTO.builder()
                .description(coupon.getDescription())
                .build();
    }

    public static Coupon toCoupon(CouponDTO dto){
        return Coupon.builder()
                .description(dto.getDescription())
                .build();
    }
}
