package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.enums.OrderStatus;

public record OrderUpdateResponse(Long id,
                                  OrderStatus orderStatus) {
}
