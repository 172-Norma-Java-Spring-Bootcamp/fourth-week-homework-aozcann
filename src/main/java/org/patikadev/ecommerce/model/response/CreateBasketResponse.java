package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.enums.BasketStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record CreateBasketResponse(Long id,
                                   Set<BasketItem> basketItemList,
                                   BigDecimal shippingPrice,
                                   BigDecimal taxPrice,
                                   BigDecimal totalQuantity,
                                   BigDecimal totalDiscount,
                                   BigDecimal totalPrice,
                                   BasketStatus status,
                                   GetCustomerResponse customer) {
}
