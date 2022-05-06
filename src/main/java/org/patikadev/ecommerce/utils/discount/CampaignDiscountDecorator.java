package org.patikadev.ecommerce.utils.discount;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class CampaignDiscountDecorator implements CampaignDiscount {

    protected CampaignDiscount campaignDiscount;

    @Override
    public BigDecimal apply(BigDecimal price) {
        return campaignDiscount.apply(price);
    }

    public BigDecimal apply(BigDecimal price, BigDecimal discount) {
        return campaignDiscount.apply(price);
    }

}
