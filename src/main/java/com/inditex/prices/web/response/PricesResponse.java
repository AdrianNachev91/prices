package com.inditex.prices.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricesResponse {

    private Integer productId;
    private String brand;
    private Integer rate;
    private Double finalPrice;
}
