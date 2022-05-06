package org.patikadev.ecommerce.utils;

import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.model.BasketItem;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.BigDecimal.ZERO;

// This class about calculate basket item price
@Slf4j
public class Calculator {

    // This method about calculate basket items price, it calculates totalItemListPrice without tax,discount and shipping price
    public static BigDecimal getItemListPrice(Set<BasketItem> basketItems) {
        BigDecimal totalItemListPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalItemListPrice = totalItemListPrice.add(basketItem.getPrice().multiply(basketItem.getQuantity()));
        }
        return totalItemListPrice;
    }

    // This method about calculate basket item tax price
    public static BigDecimal getTaxPrice(BigDecimal price, BigDecimal quantity, BigDecimal taxRate) {
        return price.multiply(quantity).multiply(taxRate);
    }

    // This method about calculate basket items tax price, it calculates totalTaxPrice
    public static BigDecimal getTotalTaxPrice(Set<BasketItem> basketItems) {
        BigDecimal totalTaxPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalTaxPrice = totalTaxPrice.add(basketItem.getTaxPrice());
        }
        return totalTaxPrice;
    }

    // This method about calculate basket items shipping price, it calculates totalShippingPrice
    public static BigDecimal getTotalShippingPrice(Set<BasketItem> basketItems) {
        BigDecimal totalShippingPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalShippingPrice = totalShippingPrice.add(basketItem.getShippingPrice());
        }
        return totalShippingPrice;
    }

    // This method about calculate basket items total price, it calculates totalPrice with tax and shipping price
    public static BigDecimal getTotalPrice(BigDecimal price, BigDecimal tax, BigDecimal shipping) {
        return price.add(tax).add(shipping);
    }

    // This method about calculate total quantity for basket
    public static BigDecimal getTotalQuantity(Set<BasketItem> basketItems) {

        return basketItems.stream()
                .map(BasketItem::getQuantity)
                .reduce(ZERO, BigDecimal::add);
    }
}
