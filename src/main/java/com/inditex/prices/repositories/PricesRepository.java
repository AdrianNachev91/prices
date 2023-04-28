package com.inditex.prices.repositories;

import com.inditex.prices.entities.Prices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PricesRepository extends CrudRepository<Prices, Integer> {

    @Query("SELECT p FROM Prices p JOIN p.brand b " +
            "WHERE p.startDate <= :applicationDate AND p.endDate >= :applicationDate AND p.productId = :productId AND b.brandName = :brand " +
            "ORDER BY p.priority DESC LIMIT 1")
    Prices findHighestPriorityByDateProductAndBrand(@Param("applicationDate") LocalDateTime applicationDate,
                                                    @Param("productId") Long productId,
                                                    @Param("brand") String brand);
}
