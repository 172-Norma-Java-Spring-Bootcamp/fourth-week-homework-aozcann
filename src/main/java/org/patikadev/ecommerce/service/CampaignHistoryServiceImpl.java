package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.model.CampaignHistory;
import org.patikadev.ecommerce.repository.CampaignHistoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignHistoryServiceImpl implements CampaignHistoryService {

    private final CampaignHistoryRepository campaignHistoryRepository;

    @Override
    public void create(Long customerId, Long campaignId, BigDecimal discountAmount) {
        CampaignHistory campaignHistory = new CampaignHistory();
        campaignHistory.setCustomerId(customerId);
        campaignHistory.setCampaignId(campaignId);
        campaignHistory.setDiscountAmount(discountAmount);
        campaignHistory.setCreatedAt(new Date());
        campaignHistory.setCreatedBy("AhmetOzcan");
        CampaignHistory savedCampaignHistory = campaignHistoryRepository.save(campaignHistory);
        log.info("campaignHistory returned successfully by id -> {}", savedCampaignHistory.getId());

    }
}
