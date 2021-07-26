package com.zuka.coupon.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import com.zuka.coupon.models.Coupon;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {
}
