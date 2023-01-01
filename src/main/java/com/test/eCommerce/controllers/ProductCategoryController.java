package com.test.eCommerce.controllers;

import com.test.eCommerce.entity.ProductCategory;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eCommerce")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/get/productCategories")
    private List<ProductCategory> getAllProductCategories() {
        return productCategoryService.findAllProductCategories();
    }

    @GetMapping("/get/productCategoryById/{id}")
    private ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") int id) {
        return productCategoryService.getProductCategoryById(id);

    }

    @PostMapping("/save/productCategory")
    private ResponseEntity<Message> saveProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.saveProductCategory(productCategory);
    }

    @PutMapping("/update/productCategory/{id}")
    private ResponseEntity<Message> updateProductCategory(@PathVariable int id, @RequestBody ProductCategory productCategory) {
        return productCategoryService.updateProductCategory(id, productCategory);
    }

    @DeleteMapping("/delete/productCategory/{id}")
    private ResponseEntity<Message> deleteProductCategory(@PathVariable int id) {
        return productCategoryService.deleteProductCategory(id);
    }
}
