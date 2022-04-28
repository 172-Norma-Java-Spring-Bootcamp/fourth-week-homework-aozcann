package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.BasketItem;
import org.patikadev.ecommerce.model.enums.AddOrDeleteStatus;

import java.util.Set;

public record AddOrDeleteBasketRequest(
        Set<BasketItem> items,
        AddOrDeleteStatus status) {
}
