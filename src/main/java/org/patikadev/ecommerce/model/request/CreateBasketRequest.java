package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.Campaign;
import org.patikadev.ecommerce.model.enums.PaymentType;

import java.util.List;

public record CreateBasketRequest(Long customerId,
                                  List<BasketItem> items,
                                  PaymentType paymentType,
                                  Campaign campaign) {
}
