package com.test.eCommerce.dto;

import com.test.eCommerce.entity.ProductCategory;
import com.test.eCommerce.entity.ProductDiscount;
import org.springframework.stereotype.Component;


import java.util.Set;
@Component
public class ProductDto {

    private int id;

    private String name;

    private String code;

    private float price;

    private Set<ProductDiscount> productDiscounts;

    private ProductCategory productCategory;
}
