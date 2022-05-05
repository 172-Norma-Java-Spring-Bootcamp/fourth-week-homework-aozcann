package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CampaignHistory extends BaseExtendedModel{

    Long customerId;
    Long campaignId;
    BigDecimal discountAmount;
}
