package com.zuka.coupon.services;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.mappers.CouponMapper;
import com.zuka.coupon.repositorys.CouponRepository;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public CouponDTO save(CouponDTO dto) {
       return CouponMapper.toDTO(couponRepository.save(CouponMapper.toCoupon(dto)));
    }

    @Override
    public List<CouponDTO> listAll() {
        List<CouponDTO> list = new ArrayList<>();
        couponRepository.findAll().forEach(p-> list.add(CouponMapper.toDTO(p)));
        return list;
    }

    @Override
    public CouponDTO findById() {
        return null;
    }
}
