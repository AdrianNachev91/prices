package com.inditex.prices.entities;

import com.inditex.prices.entities.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity(name = "Prices")
@Table(name = "Prices")
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Prices {

    @Id
    @Column(nullable = false, name = "PRICE_LIST")
    private Long priceList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brands brand;

    @Column(nullable = false, name = "START_DATE")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "END_DATE")
    private LocalDateTime endDate;

    @Column(nullable = false, name = "PRODUCT_ID")
    private Long productId;

    @Column(nullable = false, name = "PRIORITY")
    private Integer priority;

    @Column(nullable = false, name = "PRICE")
    private Double price;

    @Column(nullable = false, name = "CURR")
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
