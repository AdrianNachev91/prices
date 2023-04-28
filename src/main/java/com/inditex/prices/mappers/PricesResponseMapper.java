package com.inditex.prices.mappers;

import com.inditex.prices.entities.Prices;
import com.inditex.prices.web.response.PricesResponse;

public class PricesResponseMapper {

    public PricesResponse fromPricesEntity(Prices price) {
        return PricesResponse.builder()
                .productId(price.getProductId())
                .brand(price.getBrand().getBrandName())
                .rate(price.getPriceList())
                .finalPrice(price.getPrice())
                .build();
    }
}
