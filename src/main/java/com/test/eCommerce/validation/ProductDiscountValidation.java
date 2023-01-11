package com.test.eCommerce.validation;

import com.test.eCommerce.entity.ProductDiscount;
import com.test.eCommerce.messages.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDiscountValidation {

    public ResponseEntity<Message> checkProductDiscount(ProductDiscount productDiscount) {
        if (productDiscount.getLowerLimit() <= 0) {
            return new ResponseEntity<>(new Message("Lower Limit cannot equal minus values", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        } else if (!(productDiscount.getLowerLimit() <= productDiscount.getUpperLimit())) {
            return new ResponseEntity<>(new Message("upper limit should be higher than the lower limit", HttpStatus.BAD_REQUEST), HttpStatus.OK);
        }
        return null;
    }

}
