package com.test.eCommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "PROMOTION")
@Setter
@Getter
@AllArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String type;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String promoCode;
}
