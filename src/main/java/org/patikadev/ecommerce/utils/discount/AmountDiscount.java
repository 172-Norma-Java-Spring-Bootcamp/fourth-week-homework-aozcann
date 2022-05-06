package org.patikadev.ecommerce.utils.discount;

import org.patikadev.ecommerce.exception.BusinessServiceOperationException;

import java.math.BigDecimal;

public class AmountDiscount extends CampaignDiscountDecorator {

    public AmountDiscount(CampaignDiscount campaignDiscount) {
        super(campaignDiscount);
    }

    @Override
    public BigDecimal apply(BigDecimal price, BigDecimal discount) {

        BigDecimal discountedPrice = super.apply(price.subtract(discount));

        if (discountedPrice.compareTo(discount) <= 0) {
            throw new BusinessServiceOperationException.DiscountAmountCanNotBeBiggerThanTotalBasketPrice("Discount amount can not be bigger than total basket price");
        }
        return discountedPrice;
    }
}
