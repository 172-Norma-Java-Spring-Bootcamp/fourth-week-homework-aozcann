package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.Order;
import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.enums.PaymentStatus;
import org.patikadev.ecommerce.model.request.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class OrderConverterImpl implements OrderConverter {

    @Override
    public Order toCreateOrder(PaymentRequest request, Basket basket) {

        Order order = new Order();
        order.setBasket(basket);
        order.setStatus(OrderStatus.INIT);
        order.setCardNumber(request.cardNumber());
        order.setCardOwnerName(request.cardOwnerName());
        order.setCardLastDate(request.cardLastDate());
        order.setCcv(request.ccv());
        order.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
        order.setPaymentType(request.paymentType());
        order.setCustomer(basket.getCustomer());
        order.setCreatedAt(new Date());
        order.setCreatedBy("AhmetOzcan");

        return order;
    }
}
