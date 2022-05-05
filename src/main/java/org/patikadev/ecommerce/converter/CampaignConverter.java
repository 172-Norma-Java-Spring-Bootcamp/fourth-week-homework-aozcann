package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Campaign;
import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.patikadev.ecommerce.model.response.CreateCampaignResponse;
import org.patikadev.ecommerce.model.response.GetCampaignResponse;

public interface CampaignConverter {
    Campaign toCreateCampaign(CreateCampaignRequest request);

    CreateCampaignResponse toCreateCampaignResponse(Campaign campaign);

    GetCampaignResponse toGetCampaignResponse(Campaign campaign);
}
