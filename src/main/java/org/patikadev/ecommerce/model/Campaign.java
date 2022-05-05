package org.patikadev.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.patikadev.ecommerce.model.enums.CampaignType;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
public class Campaign extends BaseExtendedModel{

    String name;
    String code;
    CampaignType type;
    BigDecimal discount; // It can be rate or amount according to Campaign type.
    BigDecimal usageLimit; // Total usage limit
    BigDecimal currentUsageCount;
    @JsonProperty("dateOfStart")
    Date startDate; // Beginning of validity date
    @JsonProperty("dateOfEnd")
    Date endDate; // Ending of validity date

//    @ManyToOne
//    Customer customer;



}
