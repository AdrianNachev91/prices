package com.inditex.prices.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricesResponse {

    private Long productId;
    private String brand;
    private Long rate;
    private Double finalPrice;
}
