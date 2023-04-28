package com.inditex.prices.services;

import com.inditex.prices.entities.Prices;
import com.inditex.prices.mappers.PricesResponseMapper;
import com.inditex.prices.repositories.PricesRepository;
import com.inditex.prices.web.response.PricesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PricesService {

    private final PricesRepository pricesRepository;

    public PricesResponse findHighestPriorityByDateProductAndBrand(LocalDateTime applicationDate, Long productId, String brand) {
        Prices price = pricesRepository.findHighestPriorityByDateProductAndBrand(applicationDate, productId, brand);
        return new PricesResponseMapper().fromPricesEntity(price);
    }
}
