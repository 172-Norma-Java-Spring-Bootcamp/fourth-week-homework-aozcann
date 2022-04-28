package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Basket extends BaseExtendedModel {

    @Column(nullable = false)
    private BigDecimal price;

//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
    private BigDecimal totalQuantity = BigDecimal.ZERO;


    @Column(nullable = false)
    private BigDecimal totalPrice;

//    @OneToMany(mappedBy = "basket",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BasketItem> items = new HashSet<>();


}
