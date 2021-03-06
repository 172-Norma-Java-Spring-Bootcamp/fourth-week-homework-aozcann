package org.patikadev.ecommerce.utils.discount;

import java.math.BigDecimal;

public class RateDiscount extends CampaignDiscountDecorator {

    public RateDiscount(CampaignDiscount campaignDiscount) {
        super(campaignDiscount);
    }

    @Override
    public BigDecimal apply(BigDecimal price, BigDecimal discount) {
        // It subtracts to one because, it needs to price with discount
        return super.apply(price.multiply(BigDecimal.valueOf(1).subtract(discount)));
    }
}
