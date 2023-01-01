package com.test.eCommerce.repository;

import com.test.eCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCode(String code);

}
