package org.patikadev.ecommerce.utils.discount;

import java.math.BigDecimal;

public interface CampaignDiscount {

    BigDecimal apply(BigDecimal price);

}
