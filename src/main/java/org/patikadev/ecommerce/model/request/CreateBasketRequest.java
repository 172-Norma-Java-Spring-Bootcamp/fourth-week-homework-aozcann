package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.enums.PaymentType;

import java.util.Set;

public record CreateBasketRequest(Long customerId,
                                  Set<BasketItem> items,
                                  PaymentType paymentType,
                                  String campaignCode) {
}
