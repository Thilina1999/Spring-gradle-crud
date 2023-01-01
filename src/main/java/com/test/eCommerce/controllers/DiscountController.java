package com.test.eCommerce.controllers;

import com.test.eCommerce.entity.Discount;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eCommerce")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/get/allDiscounts")
    private List<Discount> getAllDiscount() {
        return discountService.findAllProductCategories();
    }

    @GetMapping("/get/discountById/{id}")
    private ResponseEntity<Discount> getDiscountById(@PathVariable("id") int id) {
        return discountService.getDiscountById(id);
    }

    @PostMapping("/save/discount")
    private ResponseEntity<Message> saveDiscount(@RequestBody Discount discount) {
        return discountService.saveDiscount(discount);
    }

    @PutMapping("/update/discount/{id}")
    private ResponseEntity<Message> updateDiscount(@PathVariable("id") int id, @RequestBody Discount discount) {
        return discountService.updateDiscount(id, discount);
    }
    @DeleteMapping("/delete/discount/{id}")
    private ResponseEntity<Message> deleteDiscount(@PathVariable("id") int id) {
        return discountService.deleteDiscount(id);
    }
}
