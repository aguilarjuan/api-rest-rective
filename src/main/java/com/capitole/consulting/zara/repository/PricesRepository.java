package com.capitole.consulting.zara.repository;

import com.capitole.consulting.zara.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Long> {

   List<Prices> findByProductIdAndBrandId(Long productId, Integer group);
   Prices findByPriceList(Long priceList);
}
