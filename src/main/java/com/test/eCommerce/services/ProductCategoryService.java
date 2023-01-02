package com.test.eCommerce.services;

import com.test.eCommerce.entity.ProductCategory;
import com.test.eCommerce.messages.Message;
import com.test.eCommerce.repository.ProductCategoryRepository;
import com.test.eCommerce.validation.ProductCategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductCategoryValidation productCategoryValidation;

    public List<ProductCategory> findAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ResponseEntity<ProductCategory> getProductCategoryById(int id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);
        return productCategoryOptional.map(productCategory -> new ResponseEntity<>(productCategory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Message> saveProductCategory(ProductCategory productCategory) {
        if (productCategoryValidation.checkProductCategory(productCategory, productCategoryRepository) != null) {
            return productCategoryValidation.checkProductCategory(productCategory, productCategoryRepository);
        }
        productCategoryRepository.save(productCategory);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, productCategory), HttpStatus.OK);

    }

    public ResponseEntity<Message> updateProductCategory(int id, ProductCategory productCategory) {
        ProductCategory oldProductCategory = productCategoryRepository.findById(id).orElse(null);
        if (oldProductCategory == null) {
            return new ResponseEntity<>(new Message("Product Category not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        if (!Objects.equals(oldProductCategory.getCode(), productCategory.getCode())) {
            if (productCategoryValidation.checkProductCategory(productCategory, productCategoryRepository) != null) {
                return productCategoryValidation.checkProductCategory(productCategory, productCategoryRepository);
            }
        }
        productCategory.setId(id);
        productCategoryRepository.save(productCategory);
        return new ResponseEntity<>(new Message("Product Category saved successfully", HttpStatus.OK, productCategory), HttpStatus.OK);
    }

    public ResponseEntity<Message> deleteProductCategory(int id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElse(null);
        if (productCategory == null) {
            return new ResponseEntity<>(new Message("Product Category not found", HttpStatus.NOT_FOUND), HttpStatus.OK);
        }

        productCategoryRepository.delete(productCategory);
        return new ResponseEntity<>(new Message("Product Category deleted successfully", HttpStatus.OK, productCategory), HttpStatus.OK);
    }

}
