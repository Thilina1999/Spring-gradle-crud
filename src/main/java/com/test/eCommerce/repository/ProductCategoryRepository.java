package com.test.eCommerce.repository;

import com.test.eCommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByName(String name);

    List<ProductCategory> findByCode(String code);
}
