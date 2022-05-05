package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface CampaignHistoryService {

    void create(Long customerId, Long campaignId, BigDecimal discountAmount);


}
