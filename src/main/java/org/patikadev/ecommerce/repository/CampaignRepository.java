package org.patikadev.ecommerce.repository;

import org.patikadev.ecommerce.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findByCodeAndIsDeleted(String code,boolean isDeleted);

    Collection<Campaign> findAllByIsDeleted(boolean isDeleted);
}
