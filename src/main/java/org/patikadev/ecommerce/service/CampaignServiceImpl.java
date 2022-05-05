package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.CampaignConverter;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Campaign;
import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.patikadev.ecommerce.model.response.CreateCampaignResponse;
import org.patikadev.ecommerce.model.response.GetCampaignResponse;
import org.patikadev.ecommerce.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    private final CampaignConverter campaignConverter;
    private final CampaignRepository campaignRepository;

    @Override
    public CreateCampaignResponse create(CreateCampaignRequest request) {
        Campaign campaign = campaignConverter.toCreateCampaign(request);
        campaignRepository.save(campaign);
        log.info("Campaign created successfully by id -> {}", campaign.getId());
        return campaignConverter.toCreateCampaignResponse(campaign);
    }

    @Override
    public GetCampaignResponse getCampaignById(Long id) {
        Campaign campaign = campaignRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CampaignNotFoundException("Campaign Not found"));
        log.info("Campaign returned successfully by id -> {}", campaign.getId());
        return campaignConverter.toGetCampaignResponse(campaign);
    }

    @Override
    public GetCampaignResponse getCampaignByCode(String code) {
        Campaign campaign = campaignRepository
                .findByCodeAndIsDeleted(code, false)
                .orElseThrow(() -> new BusinessServiceOperationException.CampaignNotFoundException("Campaign not found"));
        log.info("Campaign returned successfully by id -> {}", campaign.getId());
        return campaignConverter.toGetCampaignResponse(campaign);
    }

    @Override
    public Collection<GetCampaignResponse> getAllCampaign() {
        List<GetCampaignResponse> campaignResponseList = campaignRepository.findAllByIsDeleted(false)
                .stream()
                .map(campaignConverter::toGetCampaignResponse)
                .toList();
        log.info("Campaign list returned successfully.");
        return campaignResponseList;
    }
}
