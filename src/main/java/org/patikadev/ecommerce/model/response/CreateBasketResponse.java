package org.patikadev.ecommerce.model.response;

import java.math.BigDecimal;
import java.util.Set;

public record CreateBasketResponse(Long id,
                                   Set<GetBasketItemResponse> basketItemList,
                                   BigDecimal shippingPrice,
                                   BigDecimal taxPrice,
                                   BigDecimal totalQuantity,
                                   BigDecimal totalDiscount,
                                   BigDecimal totalPrice,
                                   GetCustomerResponse customer) {
}
