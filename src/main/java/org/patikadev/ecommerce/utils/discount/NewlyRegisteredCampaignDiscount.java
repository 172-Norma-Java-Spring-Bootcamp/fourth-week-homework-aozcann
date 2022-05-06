package org.patikadev.ecommerce.utils.discount;

import java.math.BigDecimal;

public class NewlyRegisteredCampaignDiscount implements CampaignDiscount {

    public static final BigDecimal PERCENT_TWENTY = BigDecimal.valueOf(0.2);

    @Override
    public BigDecimal apply(BigDecimal price) {
        // It subtracts to one because, it needs to price with discount
        return price.multiply(BigDecimal.valueOf(1).subtract(PERCENT_TWENTY));
    }
}
