package com.test.eCommerce.services;


import com.test.eCommerce.entity.ProductDiscount;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductCategoryRepository;
import com.test.eCommerce.repository.ProductDiscountRepository;
import com.test.eCommerce.validation.ProductDiscountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductDiscountService {

    @Autowired
    private ProductDiscountRepository productDiscountRepository;

    @Autowired
    private ProductDiscountValidation productDiscountValidation;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductDiscount> findAllProductCategories() {
        return productDiscountRepository.findAll();
    }

    public ResponseEntity<ProductDiscount> getProductDiscountById(int id) {
        Optional<ProductDiscount> productDiscountOptional = productDiscountRepository.findById(id);
        return productDiscountOptional.map(productDiscount -> new ResponseEntity<>(productDiscount, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Message> saveProductDiscount(ProductDiscount productDiscount) {
        if (productDiscountValidation.checkProductDiscount(productDiscount) != null) {
            return productDiscountValidation.checkProductDiscount(productDiscount);
        }
        productDiscountRepository.save(productDiscount);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, productDiscount), HttpStatus.OK);

    }

    public ResponseEntity<Message> updateProductDiscount(int id, ProductDiscount productDiscount) {
        ProductDiscount oldProductDiscount = productDiscountRepository.findById(id).orElse(null);
        if (oldProductDiscount == null) {
            return new ResponseEntity<>(new Message("Product Discount not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        if (productDiscountValidation.checkProductDiscount(productDiscount) != null) {
            return productDiscountValidation.checkProductDiscount(productDiscount);
        }

        productDiscount.setId(id);
        productDiscountRepository.save(productDiscount);
        return new ResponseEntity<>(new Message("Product Discount saved successfully", HttpStatus.OK, productDiscount), HttpStatus.OK);
    }

    public ResponseEntity<Message> deleteProductDiscount(int id) {
        ProductDiscount productDiscount = productDiscountRepository.findById(id).orElse(null);
        if (productDiscount == null) {
            return new ResponseEntity<>(new Message("Product Discount not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        productDiscountRepository.delete(productDiscount);
        return new ResponseEntity<>(new Message("Product Category deleted successfully", HttpStatus.OK, productDiscount), HttpStatus.OK);
    }


}
