package com.inditex.prices.repositories;

import com.inditex.prices.entities.Brands;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandsRepository extends CrudRepository<Brands, Integer> {
}
