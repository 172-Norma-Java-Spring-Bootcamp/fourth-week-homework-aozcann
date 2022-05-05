package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Campaign;
import org.patikadev.ecommerce.model.CampaignHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface CampaignHistoryRepository extends JpaRepository<CampaignHistory, Long> {

    Collection<CampaignHistory> findAllByCustomerIdAndCampaignId(Long customerId,Long campaignId);
    Collection<CampaignHistory> findAllByCustomerId(Long customerId);

//    Optional<CampaignHistory> findByIdAndCount(Long id, BigDecimal count);

}
