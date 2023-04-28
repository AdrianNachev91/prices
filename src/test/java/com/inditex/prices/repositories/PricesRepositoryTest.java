package com.inditex.prices.repositories;

import com.inditex.prices.entities.Brands;
import com.inditex.prices.entities.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static com.inditex.prices.entities.enums.Currency.EUR;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext
class PricesRepositoryTest {

    @Autowired
    private PricesRepository pricesRepository;
    @Autowired
    private BrandsRepository brandsRepository;

    @BeforeEach
    void setUp() {

        var brand = Brands.builder().id(1L).brandName("ZARA").build();
        brandsRepository.save(brand);

        pricesRepository.save(
                Prices.builder()
                        .priceList(1L).brand(brand).startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                        .endDate(LocalDateTime.parse("2020-12-31T23:59:59")).productId(35455L).priority(0)
                        .price(35.50).currency(EUR)
                        .build()
        );
        pricesRepository.save(
                Prices.builder()
                        .priceList(2L).brand(brand).startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                        .endDate(LocalDateTime.parse("2020-06-14T18:30:00")).productId(35455L).priority(1)
                        .price(25.45).currency(EUR)
                        .build()
        );
        pricesRepository.save(
                Prices.builder()
                        .priceList(3L).brand(brand).startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
                        .endDate(LocalDateTime.parse("2020-06-15T11:00:00")).productId(35455L).priority(1)
                        .price(30.50).currency(EUR)
                        .build()
        );
        pricesRepository.save(
                Prices.builder()
                        .priceList(4L).brand(brand).startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                        .endDate(LocalDateTime.parse("2020-12-31T23:59:59")).productId(35455L).priority(1)
                        .price(38.95).currency(EUR)
                        .build()
        );
    }

    @DisplayName("Request at 10:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @Test
    void test_AssignmentTest1() {

        // Test
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(price).isNotNull();
        assertThat(price.getPriceList()).isEqualTo(1);
    }

    @DisplayName("Request at 16:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @Test
    void test_AssignmentTest2() {

        // Test
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T16:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(price).isNotNull();
        assertThat(price.getPriceList()).isEqualTo(2);
    }

    @DisplayName("Request at 21:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @Test
    void test_AssignmentTest3() {

        // Test
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T21:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(price).isNotNull();
        assertThat(price.getPriceList()).isEqualTo(1);
    }

    @DisplayName("Request at 10:00 on the 15th for the product 35455 for brand 1 (ZARA).")
    @Test
    void test_AssignmentTest4() {

        // Test
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-15T10:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(price).isNotNull();
        assertThat(price.getPriceList()).isEqualTo(3);
    }

    @DisplayName("Request at 21:00 on the 16th for the product 35455 for brand 1 (ZARA).")
    @Test
    void test_AssignmentTest5() {

        // Test
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-16T21:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(price).isNotNull();
        assertThat(price.getPriceList()).isEqualTo(4);
    }
}