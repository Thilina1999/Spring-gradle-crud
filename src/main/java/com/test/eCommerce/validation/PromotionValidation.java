package com.test.eCommerce.validation;

import com.test.eCommerce.entity.Promotion;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PromotionValidation {
    public ResponseEntity<Message> checkPromotion(Promotion promotion, PromotionRepository promotionRepository) {
        if (promotion.getPromoCode().isEmpty()) {
            return new ResponseEntity<>(new Message("Please fill Promo Code field", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        } else if (promotionRepository.findByPromoCode(promotion.getPromoCode()).size() > 0) {
            return new ResponseEntity<>(new Message("Promotion code already exists", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
        return null;
    }
}
