package com.zuka.coupon;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.models.Coupon;
import com.zuka.coupon.repositorys.CouponRepository;
import com.zuka.coupon.services.CouponService;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.CompositeIterator;
import static org.mockito.Mockito.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@MicronautTest
class CouponAPITest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    CouponService couponService;

    @Inject
    CouponRepository couponRepository;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testSave() {
        CouponDTO result = couponService.save(CouponDTO.builder()
                                                       .description("Test")
                                                       .build());

        Assertions.assertEquals("Test", result.getDescription());
    }

    @Test
    void getAll(){
        List<Coupon> listCoupon = Arrays.asList(Coupon.builder()
                                                    .description("Test")
                                                    .build());

        Mockito.when(couponRepository.findAll()).thenReturn(listCoupon);
        List<CouponDTO> list = couponService.listAll();
        Assertions.assertTrue(list.size() > 0);
    }

    @MockBean(CouponRepository.class)
    CouponRepository couponRepository() {
        return mock(CouponRepository.class);
    }

}
