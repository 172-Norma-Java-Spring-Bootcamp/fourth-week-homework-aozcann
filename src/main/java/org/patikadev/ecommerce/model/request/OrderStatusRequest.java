package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.enums.OrderStatus;

public record OrderStatusRequest(OrderStatus status) {
}
