package com.zuka.coupon.controllers;

import com.zuka.coupon.dtos.CouponDTO;
import com.zuka.coupon.services.CouponService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@Controller("/coupon")
public class CouponController {

    private final CouponService service;

    @Inject
    public CouponController(CouponService service) {
        this.service = service;
    }

    @Post
    @Operation(summary = "Save the coupon")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Tag(name = "coupon")
    public HttpResponse<?> save(@Body CouponDTO dto) {
        return HttpResponse.created(service.save(dto));
    }

    @Get
    @Operation(summary = "Return the all Coupon")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON),
            responseCode = "400", description = "Invalid coupon specified"
    )
    @Tag(name = "coupon")
    public HttpResponse<List<CouponDTO>> getAll() {
        return HttpResponse.ok().body(service.listAll());
    }

    @Get("/{id}")
    public HttpResponse<CouponDTO> getById(@PathVariable Long id) {
        CouponDTO couponDTO = service.findById(id);
        if (Objects.isNull(couponDTO)) {
            return HttpResponse.noContent();
        }
        return HttpResponse.ok().body(couponDTO);
    }
}
