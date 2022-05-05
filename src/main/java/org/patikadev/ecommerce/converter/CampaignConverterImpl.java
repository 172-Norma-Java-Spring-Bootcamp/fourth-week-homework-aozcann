package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.Campaign;
import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.patikadev.ecommerce.model.response.CreateCampaignResponse;
import org.patikadev.ecommerce.model.response.GetCampaignResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class CampaignConverterImpl implements CampaignConverter {
    @Override
    public Campaign toCreateCampaign(CreateCampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.name());
        campaign.setCode(request.code());
        campaign.setType(request.type());
        campaign.setDiscount(request.discount());
        campaign.setUsageLimit(request.limit());
        campaign.setStartDate(request.startDate());
        campaign.setEndDate(request.endDate());
        campaign.setCreatedAt(new Date());
        campaign.setCreatedBy("AhmetOzcan");

        return campaign;
    }

    @Override
    public CreateCampaignResponse toCreateCampaignResponse(Campaign campaign) {
        return new CreateCampaignResponse(campaign.getName(),
                campaign.getType(),
                campaign.getDiscount(),
                campaign.getUsageLimit(),
                campaign.getStartDate(),
                campaign.getEndDate());
    }

    @Override
    public GetCampaignResponse toGetCampaignResponse(Campaign campaign) {
        return new GetCampaignResponse(campaign.getId(),
                campaign.getName(),
                campaign.getCode(),
                campaign.getType(),
                campaign.getDiscount(),
                campaign.getUsageLimit(),
                campaign.getStartDate(),
                campaign.getEndDate());
    }
}
