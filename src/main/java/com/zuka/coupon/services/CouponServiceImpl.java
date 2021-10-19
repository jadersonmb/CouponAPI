package com.zuka.coupon.services;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.mappers.CouponMapper;
import com.zuka.coupon.models.Coupon;
import com.zuka.coupon.repositorys.CouponRepository;
import io.micronaut.data.jpa.repository.criteria.Specification;
import jakarta.inject.Singleton;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public CouponDTO findById(Long id) {
        couponRepository.findAll(searchDesc(CouponDTO.builder().build()));
        return couponRepository.findById(id).map(CouponMapper::toDTO).orElse(new CouponDTO());
    }

    public static Specification<Coupon> searchDesc(CouponDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(filter.getDescription()) && !filter.getDescription().isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("name")),
                        "%".concat(filter.getDescription().toLowerCase()).concat("%")));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
