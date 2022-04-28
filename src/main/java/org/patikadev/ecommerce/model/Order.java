package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.enums.PaymentStatus;
import org.patikadev.ecommerce.model.enums.PaymentType;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "customer_order")
public class Order extends BaseExtendedModel{

    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String cardNumber;
    private String cardOwnerName;
    private String cardLastDate;
    private String ccv;
}
