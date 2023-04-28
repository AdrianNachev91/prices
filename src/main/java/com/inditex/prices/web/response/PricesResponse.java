package com.inditex.prices.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PricesResponse {

    @Schema(example = "35455")
    private Long productId;
    @Schema(example = "ZARA")
    private String brand;
    @Schema(example = "4")
    private Long rate;
    @Schema(example = "38.95")
    private Double finalPrice;
}
