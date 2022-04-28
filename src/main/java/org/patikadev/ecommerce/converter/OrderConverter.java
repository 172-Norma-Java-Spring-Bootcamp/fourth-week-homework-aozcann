package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Basket;
import org.patikadev.ecommerce.model.Order;
import org.patikadev.ecommerce.model.request.PaymentRequest;

public interface OrderConverter {
    Order toCreateOrder(PaymentRequest request,Basket basket);
}
