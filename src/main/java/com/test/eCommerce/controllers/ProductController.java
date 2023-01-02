package com.test.eCommerce.controllers;

import com.test.eCommerce.entity.Product;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eCommerce")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/get/allProducts")
    private List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/get/productById/{id}")
    private ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/save/product")
    private ResponseEntity<Message> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update/product/{id}")
    private ResponseEntity<Message> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/product/{id}")
    private ResponseEntity<Message> deleteProduct(@PathVariable("id") int id) {
        return productService.deleteProduct(id);
    }
}
