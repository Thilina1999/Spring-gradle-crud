package com.test.eCommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private float price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductDiscount> productDiscounts;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private ProductCategory productCategory;
}
