package com.test.eCommerce.controllers;

import com.test.eCommerce.entity.ProductDiscount;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.services.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eCommerce")
public class ProductDiscountController {
    @Autowired
    private ProductDiscountService productDiscountService;
    @GetMapping("/get/allProductDiscounts")
    private List<ProductDiscount> getAllProductDiscount() {
        return productDiscountService.findAllProductCategories();
    }

    @GetMapping("/get/productDiscountById/{id}")
    private ResponseEntity<ProductDiscount> getProductDiscountById(@PathVariable("id") int id) {
        return productDiscountService.getProductDiscountById(id);
    }
    @PostMapping("/save/productDiscount")
    private ResponseEntity<Message> saveProductDiscount(@RequestBody ProductDiscount productDiscount) {
        return productDiscountService.saveProductDiscount(productDiscount);
    }
    @PutMapping("/update/productDiscount/{id}")
    private ResponseEntity<Message> updateProductDiscount(@PathVariable("id") int id, @RequestBody ProductDiscount productDiscount) {
        return productDiscountService.updateProductDiscount(id, productDiscount);
    }

    @DeleteMapping("/delete/productDiscount/{id}")
    private ResponseEntity<Message> deleteProductDiscount(@PathVariable("id") int id) {
        return productDiscountService.deleteProductDiscount(id);
    }
}
