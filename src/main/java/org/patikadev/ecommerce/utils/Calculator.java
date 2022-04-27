package org.patikadev.ecommerce.utils;

import org.patikadev.ecommerce.model.BasketItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static java.math.BigDecimal.*;


public class Calculator {

    public static BigDecimal getItemListPrice(Set<BasketItem> basketItems) {
        BigDecimal totalItemListPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalItemListPrice = totalItemListPrice.add(basketItem.getPrice().multiply(basketItem.getQuantity()));
        }
        return totalItemListPrice;
    }

    public static BigDecimal getDiscountPrice(BigDecimal price, BigDecimal quantity, BigDecimal discountRate){
        return price.multiply(quantity).multiply(discountRate);
    }

    public static BigDecimal getTotalDiscountPrice(Set<BasketItem> basketItems){
        BigDecimal totalDiscountPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalDiscountPrice = totalDiscountPrice.add(basketItem.getDiscountPrice());
        }
        return totalDiscountPrice;
    }

    public static BigDecimal getTaxPrice(BigDecimal price, BigDecimal quantity, BigDecimal taxRate){
        return price.multiply(quantity).multiply(taxRate);
    }

    public static BigDecimal getTotalTaxPrice(Set<BasketItem> basketItems){
        BigDecimal totalTaxPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalTaxPrice = totalTaxPrice.add(basketItem.getTaxPrice());
        }
        return totalTaxPrice;
    }

    public static BigDecimal getTotalShippingPrice(Set<BasketItem> basketItems){
        BigDecimal totalShippingPrice = ZERO;
        for (BasketItem basketItem : basketItems) {
            totalShippingPrice = totalShippingPrice.add(basketItem.getShippingPrice());
        }
        return totalShippingPrice;
    }

    public static BigDecimal getTotalPrice(BigDecimal price,BigDecimal tax,BigDecimal shipping,BigDecimal discount){
        return price.add(tax).add(shipping).subtract(discount);
    }

    public static BigDecimal getTotalQuantity(Set<BasketItem> basketItems) {
        return BigDecimal.valueOf(basketItems.stream().map(BasketItem::getQuantity).count());
    }
}
