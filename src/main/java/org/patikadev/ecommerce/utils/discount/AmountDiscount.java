package org.patikadev.ecommerce.utils.discount;

import java.math.BigDecimal;

public class AmountDiscount extends CampaignDiscountDecorator {


    public AmountDiscount(CampaignDiscount campaignDiscount) {
        super(campaignDiscount);
    }

    @Override
    public BigDecimal apply(BigDecimal price, BigDecimal discount) {

        return super.apply(price.subtract(discount));
    }
}
