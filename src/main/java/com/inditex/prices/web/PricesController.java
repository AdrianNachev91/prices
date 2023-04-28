package com.inditex.prices.web;

import com.inditex.prices.services.PricesService;
import com.inditex.prices.web.response.PricesResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController {

    private final PricesService pricesService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PricesResponse getPrices(
            @Parameter(name = "applicationDate", required = true)
            @RequestParam("applicationDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime applicationDate,

            @Parameter(name = "productId", required = true)
            @RequestParam("productId")
            Long productId,

            @Parameter(name = "brand", required = true)
            @RequestParam("brand")
            String brand
    ) {
        return pricesService.findHighestPriorityByDateProductAndBrand(applicationDate, productId, brand);
    }
}
