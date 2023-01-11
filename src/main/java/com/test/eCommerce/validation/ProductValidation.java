package com.test.eCommerce.validation;

import com.test.eCommerce.entity.Product;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductValidation {

    public ResponseEntity<Message> checkProduct(Product product, ProductRepository productRepository) {
        if (product.getCode().isEmpty() || product.getName().isEmpty()) {
            return new ResponseEntity<>(new Message("Please fill Promo Code field", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        } else if (productRepository.findAllByCode(product.getCode()).size() > 0) {
            return new ResponseEntity<>(new Message("Product code already exists", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
        return null;
    }
}
