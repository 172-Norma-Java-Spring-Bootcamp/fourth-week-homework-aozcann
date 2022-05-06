package org.patikadev.ecommerce.utils.discount;

import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;

import java.math.BigDecimal;

@Slf4j
public class AmountDiscount extends CampaignDiscountDecorator {

    public AmountDiscount(CampaignDiscount campaignDiscount) {
        super(campaignDiscount);
    }

    @Override
    public BigDecimal apply(BigDecimal price, BigDecimal discount) {

        // It subtracts discount amount price for basket price
        BigDecimal discountedPrice = super.apply(price.subtract(discount));

        // If amount price bigger than total basket price , discount does not use.
        if (discountedPrice.compareTo(discount) <= 0) {
            log.error("Discount amount can not be bigger than total basket price.");
            throw new BusinessServiceOperationException.DiscountAmountCanNotBeBiggerThanTotalBasketPrice("Discount amount can not be bigger than total basket price");
        }
        return discountedPrice;
    }
}
