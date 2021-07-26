package com.zuka.coupon.services;

import com.zuka.coupon.dtos.CouponDTO;

import java.util.List;

public interface CouponService {

    CouponDTO save(CouponDTO dto);
    List<CouponDTO> listAll();
    CouponDTO findById(Long id);

}
