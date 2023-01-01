package com.test.eCommerce.validation;

import com.test.eCommerce.entity.Discount;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.DiscountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DiscountValidation {
    public ResponseEntity<Message> checkDiscount(Discount discount, DiscountRepository discountRepository) {
        if (discount.getValue() == 0) {
            return new ResponseEntity<>(new Message("Please fill value for the fields", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        } else if (discountRepository.findByValue(discount.getValue()).size() > 0) {
            return new ResponseEntity<>(new Message("Discount value already exists", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
        return null;
    }
}
