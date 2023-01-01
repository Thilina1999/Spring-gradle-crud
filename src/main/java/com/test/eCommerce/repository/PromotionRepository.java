package com.test.eCommerce.repository;

import com.test.eCommerce.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findByPromoCode(String promoCode);
}
