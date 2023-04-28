package com.inditex.prices.services;

import com.inditex.prices.entities.Brands;
import com.inditex.prices.entities.Prices;
import com.inditex.prices.exceptions.PricesHttpException;
import com.inditex.prices.repositories.PricesRepository;
import com.inditex.prices.web.response.PricesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class PricesServiceTest {

    private PricesService pricesService;

    @Mock
    private PricesRepository pricesRepository;

    @BeforeEach
    void setUp() {
        pricesService = new PricesService(pricesRepository);
    }

    @Test
    void findHighestPriorityByDateProductAndBrand_HappyFlow() {

        // Prepare
        Prices price = Prices.builder()
                .brand(Brands.builder().brandName("ZARA").build())
                .priceList(1L)
                .productId(35455L)
                .price(35.5)
                .build();
        when(pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, "ZARA"))
                .thenReturn(Optional.of(price));

        // Test
        PricesResponse response = pricesService.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, "ZARA");

        // Verify
        assertThat(response).isNotNull();
        var expected = PricesResponse.builder()
                .productId(35455L)
                .brand("ZARA")
                .rate(1L)
                .finalPrice(35.5)
                .build();
        assertThat(response).isEqualTo(expected);
    }

    @Test
    void findHighestPriorityByDateProductAndBrand_NotFound() {

        // Prepare
        when(pricesRepository.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, "ZARA"))
                .thenReturn(Optional.empty());

        // Test
        Throwable e = catchThrowable(() -> pricesService.findHighestPriorityByDateProductAndBrand(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, "ZARA"));

        // Verify
        assertThat(e).isInstanceOf(PricesHttpException.class);
        assertThat(e.getMessage()).isEqualTo("Price list for this criteria not found");
        assertThat(((PricesHttpException)e).getStatusCode()).isEqualTo(NOT_FOUND);
    }
}