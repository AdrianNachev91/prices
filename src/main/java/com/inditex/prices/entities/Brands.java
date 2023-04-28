package com.inditex.prices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "Brands")
@Table(name = "Brands")
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Brands {

    @Id
    @Column(nullable = false, name = "ID")
    private Long id;

    @Column(nullable = false, name = "BRAND_NAME")
    private String brandName;
}
