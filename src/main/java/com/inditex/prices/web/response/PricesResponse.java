package com.inditex.prices.web.response;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PricesResponse {

    private Long productId;
    private String brand;
    private Long rate;
    private Double finalPrice;
}
