package com.test.eCommerce.services;

import com.test.eCommerce.entity.Discount;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.DiscountRepository;
import com.test.eCommerce.validation.DiscountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountValidation discountValidation;

    public List<Discount> findAllDiscount() {
        return discountRepository.findAll();
    }

    public ResponseEntity<Discount> getDiscountById(int id) {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        return discountOptional.map(discount -> new ResponseEntity<>(discount, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Message> saveDiscount(Discount discount) {
        if (discountValidation.checkDiscount(discount, discountRepository) != null) {
            return discountValidation.checkDiscount(discount, discountRepository);
        }
        discount.setStatus(true);
        discountRepository.save(discount);
        return new ResponseEntity<>(new Message("Discount saved successfully", HttpStatus.OK, discount), HttpStatus.OK);

    }

    public ResponseEntity<Message> updateDiscount(int id, Discount discount) {
        Discount oldDiscount = discountRepository.findById(id).orElse(null);
        if (oldDiscount == null) {
            return new ResponseEntity<>(new Message("Discount not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }
        if (oldDiscount.getValue() != discount.getValue()) {
            if (discountValidation.checkDiscount(discount, discountRepository) != null) {
                return discountValidation.checkDiscount(discount, discountRepository);
            }
        }
        discount.setId(id);
        discountRepository.save(discount);
        return new ResponseEntity<>(new Message("Discount updated successfully", HttpStatus.OK, discount), HttpStatus.OK);

    }

    public ResponseEntity<Message> deleteDiscount(int id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount == null) {
            return new ResponseEntity<>(new Message("Discount not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }
        discount.setStatus(false);
        discountRepository.save(discount);
        return new ResponseEntity<>(new Message("Discount expire successfully", HttpStatus.OK, discount), HttpStatus.OK);
    }
}
