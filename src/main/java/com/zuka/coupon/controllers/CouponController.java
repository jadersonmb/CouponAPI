package com.zuka.coupon.controllers;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.services.CouponService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import java.util.List;

@Controller("/")

public class CouponController {

    private final CouponService service;

    @Inject
    public CouponController(CouponService service) {
        this.service = service;
    }

    @Post
    public HttpResponse<?> save(@Body CouponDTO dto){
        return HttpResponse.created(service.save(dto));
    }

    @Get
    public HttpResponse<List<CouponDTO>> getAll(){
        return HttpResponse.ok().body(service.listAll());
    }
}
