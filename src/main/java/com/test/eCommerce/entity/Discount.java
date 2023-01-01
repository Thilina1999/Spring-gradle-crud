package com.test.eCommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DISCOUNT")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "value")
    private float value;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "discount")
    private Set<ProductDiscount> productDiscounts;

}
