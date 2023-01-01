package com.test.eCommerce.repository;

import com.test.eCommerce.entity.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Integer> {
}
