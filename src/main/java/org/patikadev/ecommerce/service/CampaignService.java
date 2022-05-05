package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.patikadev.ecommerce.model.request.GetCampaignCodeRequest;
import org.patikadev.ecommerce.model.response.CreateCampaignResponse;
import org.patikadev.ecommerce.model.response.GetCampaignResponse;

import java.util.Collection;

public interface CampaignService {
    CreateCampaignResponse create(CreateCampaignRequest request);

    GetCampaignResponse getCampaignById(Long id);

    GetCampaignResponse getCampaignByCode(String code);

    Collection<GetCampaignResponse> getAllCampaign();
}
