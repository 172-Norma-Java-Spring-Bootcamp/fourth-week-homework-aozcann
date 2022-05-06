package org.patikadev.ecommerce.utils;

import org.patikadev.ecommerce.model.BasketItem;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.BigDecimal.ZERO;

public class Calculator {

    public static BigDecimal getItemListPrice(Set<BasketItem> basketItems) {
        BigDecimal totalItemListPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalItemListPrice = totalItemListPrice.add(basketItem.getPrice().multiply(basketItem.getQuantity()));
        }
        return totalItemListPrice;
    }

    public static BigDecimal getTaxPrice(BigDecimal price, BigDecimal quantity, BigDecimal taxRate) {
        return price.multiply(quantity).multiply(taxRate);
    }

    public static BigDecimal getTotalTaxPrice(Set<BasketItem> basketItems) {
        BigDecimal totalTaxPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalTaxPrice = totalTaxPrice.add(basketItem.getTaxPrice());
        }
        return totalTaxPrice;
    }

    public static BigDecimal getTotalShippingPrice(Set<BasketItem> basketItems) {
        BigDecimal totalShippingPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalShippingPrice = totalShippingPrice.add(basketItem.getShippingPrice());
        }
        return totalShippingPrice;
    }

    public static BigDecimal getTotalPrice(BigDecimal price, BigDecimal tax, BigDecimal shipping) {
        return price.add(tax).add(shipping);
    }

    public static BigDecimal getTotalQuantity(Set<BasketItem> basketItems) {

        return basketItems.stream()
                .map(BasketItem::getQuantity)
                .reduce(ZERO, BigDecimal::add);
    }
}
