package com.inditex.prices.entities;

import com.inditex.prices.entities.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "Prices")
public class Prices {

    @Id
    @Column(nullable = false, name = "PRICE_LIST")
    private Integer priceList;

    @Column(nullable = false, name = "BRAND_ID")
    private Integer brandId;

    @Column(nullable = false, name = "START_DATE")
    private LocalDateTime startDate;

    @Column(nullable = false, name = "END_DATE")
    private LocalDateTime endDate;

    @Column(nullable = false, name = "PRODUCT_ID")
    private Integer productId;

    @Column(nullable = false, name = "PRIORITY")
    private Integer priority;

    @Column(nullable = false, name = "PRICE")
    private Double price;

    @Column(nullable = false, name = "CURR", columnDefinition = "VARCHAR(255) NOT NULL")
    private Currency currency;
}
