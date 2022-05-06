package org.patikadev.ecommerce.service;

import java.math.BigDecimal;

public interface CampaignHistoryService {

    void create(Long customerId, Long campaignId, BigDecimal discountAmount);

}
