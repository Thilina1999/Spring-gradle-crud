package com.test.eCommerce.controllers;

import com.test.eCommerce.entity.Promotion;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/eCommerce")
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @GetMapping("/get/allPromotions")
    private List<Promotion> getAllPromotion() {
        return promotionService.getAllPromotion();
    }

    @GetMapping("/get/promotionById/{id}")
    private ResponseEntity<Promotion> getPromotionById(@PathVariable("id") int id) {
        return promotionService.getPromotionById(id);
    }

    @PostMapping("/save/promotion")
    private ResponseEntity<Message> savePromotion(@RequestBody Promotion promotion) {
        return promotionService.savePromotion(promotion);
    }

    @PutMapping("/update/promotion/{id}")
    private ResponseEntity<Message> updatePromotion(@PathVariable("id") int id, @RequestBody Promotion promotion) {
        return promotionService.updatePromotion(id, promotion);
    }

    @DeleteMapping("/delete/promotion/{id}")
    private ResponseEntity<Message> deletePromotion(@PathVariable("id") int id) {
        return promotionService.deletePromotion(id);
    }
}