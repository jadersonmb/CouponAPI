package com.zuka.coupon;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.models.Coupon;
import com.zuka.coupon.repositorys.CouponRepository;
import com.zuka.coupon.services.CouponService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
@Slf4j
class CouponAPITest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    CouponService couponService;

    @Inject
    CouponRepository couponRepository;

    @Inject
    @Client("/") RxHttpClient client;

    @Test
    void testItWorks() {
        assertTrue(application.isRunning());
    }


    @Test
    void returnGetById(){
        Coupon entity = Coupon.builder()
                              .description("Test")
                              .build();

        when(couponRepository.findById(any())).thenReturn(Optional.ofNullable(entity));
        final CouponDTO dto = client.toBlocking().retrieve(HttpRequest.GET("/1"), CouponDTO.class);
        log.info("CouponDTO {} :", dto);

        assertEquals("Test", dto.getDescription());
    }

    @Test
    void notFoundGetById(){
        try{
            final CouponDTO dto = client.toBlocking().retrieve(HttpRequest.GET("/1"),CouponDTO.class);
            log.info("CouponDTO {} :", dto);
        }catch (HttpClientResponseException e){
            assertEquals(HttpResponse.notFound(), e.getResponse().getStatus());
        }
    }

    @Test
    void testSave() {
        when(couponRepository.save(any())).thenReturn(Coupon.builder()
                                                               .description("Test")
                                                               .build());

        CouponDTO result = couponService.save(CouponDTO.builder()
                                                       .description("Test")
                                                       .build());

        assertEquals("Test", result.getDescription());
    }

    @Test
    void getAll(){
        List<Coupon> listCoupon = Arrays.asList(Coupon.builder()
                                                    .description("Test")
                                                    .build());

        when(couponRepository.findAll()).thenReturn(listCoupon);
        List<CouponDTO> list = couponService.listAll();
        assertTrue(list.size() > 0);
    }

    @MockBean(CouponRepository.class)
    CouponRepository couponRepository() {
        return mock(CouponRepository.class);
    }

}
