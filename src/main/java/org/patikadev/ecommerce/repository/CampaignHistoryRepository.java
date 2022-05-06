package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.CampaignHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CampaignHistoryRepository extends JpaRepository<CampaignHistory, Long> {

    Collection<CampaignHistory> findAllByCustomerId(Long customerId);
}
