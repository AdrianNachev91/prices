package com.inditex.prices.web;

import com.inditex.prices.exceptions.advice.ControllerExceptionAdvice;
import com.inditex.prices.services.PricesService;
import com.inditex.prices.web.response.PricesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Prices", description = "Endpoints for managing proces")
public class PricesController {

    private final PricesService pricesService;

    @GetMapping("/{brand}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Find price list",
            description = "Find highest priority price list by date, product id and brand.",
            tags = {"Prices"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = PricesResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ControllerExceptionAdvice.PricesErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ControllerExceptionAdvice.PricesErrorResponse.class)
                            )
                    )
            }
    )
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
