package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.request.PaymentRequest;
import org.patikadev.ecommerce.model.response.OrderUpdateResponse;
import org.patikadev.ecommerce.model.response.PaymentResponse;

public interface OrderService {

    PaymentResponse payment(PaymentRequest request);

    OrderUpdateResponse updateStatus(Long id, OrderStatus status);

}
