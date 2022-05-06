package org.patikadev.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class BasketItem extends BaseExtendedModel {

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private BigDecimal quantity;
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

}
