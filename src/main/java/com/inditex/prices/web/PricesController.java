package com.inditex.prices.web;

import com.inditex.prices.services.PricesService;
import com.inditex.prices.web.response.PricesResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Validated
public class PricesController {

    private final PricesService pricesService;

    @GetMapping("/{brand}")
    @ResponseStatus(HttpStatus.OK)
    public PricesResponse getPrices(
            @Parameter(name = "applicationDate", required = true)
            @RequestParam("applicationDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime applicationDate,

            @Parameter(name = "productId", required = true)
            @RequestParam("productId")
            Long productId,

            @NotBlank
            @Parameter(name = "brand", required = true)
            @PathVariable(value = "brand")
            String brand
    ) {
        return pricesService.findHighestPriorityByDateProductAndBrand(applicationDate, productId, brand);
    }
}
