package com.test.eCommerce.services;

import com.test.eCommerce.entity.Promotion;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.PromotionRepository;
import com.test.eCommerce.validation.PromotionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionValidation promotionValidation;

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }

    public ResponseEntity<Promotion> getPromotionById(int id) {
        Optional<Promotion> promotionOptional = promotionRepository.findById(id);
        return promotionOptional.map(promotion ->
                new ResponseEntity<>(promotion, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Message> savePromotion(Promotion promotion) {
        if (promotionValidation.checkPromotion(promotion, promotionRepository) != null) {
            return promotionValidation.checkPromotion(promotion, promotionRepository);
        }
        promotionRepository.save(promotion);
        return new ResponseEntity<>(new Message("Promotion Save successfully", HttpStatus.OK, promotion), HttpStatus.OK);
    }

    public ResponseEntity<Message> updatePromotion(int id, Promotion promotion) {
        Promotion oldPromotion = promotionRepository.findById(id).orElse(null);
        if (oldPromotion == null) {
            return new ResponseEntity<>(new Message("Promotion not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        if (!Objects.equals(oldPromotion.getPromoCode(), promotion.getPromoCode())) {
            if (promotionValidation.checkPromotion(promotion, promotionRepository) != null) {
                return promotionValidation.checkPromotion(promotion, promotionRepository);
            }
        }
        promotion.setId(id);
        promotionRepository.save(promotion);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, promotion), HttpStatus.OK);
    }

    public ResponseEntity<Message> deletePromotion(int id) {
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        if (promotion == null) {
            return new ResponseEntity<>(new Message("promotion not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }
        promotionRepository.delete(promotion);
        return new ResponseEntity<>(new Message("Product Category deleted successfully", HttpStatus.OK, promotion), HttpStatus.OK);
    }
}
