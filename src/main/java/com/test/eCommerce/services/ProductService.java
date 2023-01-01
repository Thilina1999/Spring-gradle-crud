package com.test.eCommerce.services;

import com.test.eCommerce.entity.Product;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductRepository;
import com.test.eCommerce.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductValidation productValidation;


    public List<Product> getAllProduct() {
        return productRepository.findAll();

    }

    public ResponseEntity<Product> getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Message> saveProduct(Product product) {
        if (productValidation.checkProduct(product, productRepository) != null) {
            return productValidation.checkProduct(product, productRepository);
        }

        productRepository.save(product);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, product), HttpStatus.OK);

    }
}
