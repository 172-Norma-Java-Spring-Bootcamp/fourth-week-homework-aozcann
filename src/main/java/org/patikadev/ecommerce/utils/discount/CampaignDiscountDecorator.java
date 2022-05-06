package org.patikadev.ecommerce.utils.discount;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class CampaignDiscountDecorator implements CampaignDiscount {

    protected CampaignDiscount campaignDiscount;

    // The apply method in the CampaignDiscount interface has been overridden.
    @Override
    public BigDecimal apply(BigDecimal price) {
        return campaignDiscount.apply(price);
    }

    // The apply method , Discount will use RateDiscount and AmountDiscount
    public BigDecimal apply(BigDecimal price, BigDecimal discount) {
        return campaignDiscount.apply(price);
    }

}
