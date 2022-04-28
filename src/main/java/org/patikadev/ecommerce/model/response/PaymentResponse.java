package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.enums.OrderStatus;
import org.patikadev.ecommerce.model.enums.PaymentStatus;

public record PaymentResponse(Long id,
                              PaymentStatus paymentStatus,
                              OrderStatus orderStatus) {
}
