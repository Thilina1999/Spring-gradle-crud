package com.test.eCommerce.validation;

import com.test.eCommerce.entity.ProductCategory;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryValidation {
    public ResponseEntity<Message> checkProductCategory(ProductCategory productCategory, ProductCategoryRepository productCategoryRepository) {
        if (productCategory.getCode().isEmpty() || productCategory.getName().isEmpty()) {
            return new ResponseEntity<>(new Message("Please fill all the fields", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        } else if (productCategoryRepository.findByCode(productCategory.getCode()).size() > 0) {
            return new ResponseEntity<>(new Message("Product Category code already exists", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
        return null;
    }
}