package com.inditex.prices.web;

import com.inditex.prices.web.response.PricesResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Tag(name = "Prices")
public class PricesController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PricesResponse getPrices(
            @Parameter(name = "applicationDate", required = true) @RequestParam("applicationDate") LocalDateTime applicationDate,
            @Parameter(name = "productId", required = true) @RequestParam("productId") Integer productId,
            @Parameter(name = "brand", required = true) @RequestParam("brand") String brand
    ) {
        return null;
    }
}
