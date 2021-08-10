package com.zuka.coupon;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
            title = "CouponAPI",
            version = "0.1",
            description = "Create the coupon",
            license = @License(name = "MIT")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
