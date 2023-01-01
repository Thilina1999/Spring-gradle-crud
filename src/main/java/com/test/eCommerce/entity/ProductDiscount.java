package com.test.eCommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_DISCOUNT")
@Entity
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int lowerLimit;

    @Column
    private int upperLimit;
    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
