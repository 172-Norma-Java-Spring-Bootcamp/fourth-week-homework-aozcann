package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.enums.CampaignType;

import java.math.BigDecimal;
import java.util.Date;

public record CreateCampaignResponse(String name,
                                     CampaignType type,
                                     BigDecimal discount,
                                     BigDecimal limit,
                                     Date startDate,
                                     Date endDate) {
}
