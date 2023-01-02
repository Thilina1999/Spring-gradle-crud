package com.test.eCommerce.services;

import com.test.eCommerce.entity.Product;
import com.test.eCommerce.entity.Promotion;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductRepository;
import com.test.eCommerce.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public ResponseEntity<Message> updateProduct(int id, Product product) {
        Product oldProduct = productRepository.findById(id).orElse(null);
        if (oldProduct == null) {
            return new ResponseEntity<>(new Message("Product not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        if (!Objects.equals(oldProduct.getCode(), product.getCode())) {
            if (productValidation.checkProduct(product, productRepository) != null) {
                return productValidation.checkProduct(product, productRepository);
            }
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCode(product.getCode());
        productRepository.save(oldProduct);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, oldProduct), HttpStatus.OK);
    }

    public ResponseEntity<Message> deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new Message("product not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }
        productRepository.delete(product);
        return new ResponseEntity<>(new Message("Product Category deleted successfully", HttpStatus.OK, product), HttpStatus.OK);
    }

}
