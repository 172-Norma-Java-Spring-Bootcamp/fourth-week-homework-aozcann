package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.BasketItem;

import java.math.BigDecimal;
import java.util.Set;

public record AddOrDeleteUpdateResponse(Long basketId,
                                        Set<BasketItem> basketItemList,
                                        BigDecimal shippingPrice,
                                        BigDecimal taxPrice,
                                        BigDecimal totalQuantity,
                                        BigDecimal totalDiscount,
                                        BigDecimal totalPrice,
                                        GetCustomerResponse customer) {
}
