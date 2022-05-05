package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.patikadev.ecommerce.model.response.CreateCampaignResponse;
import org.patikadev.ecommerce.model.response.GetCampaignResponse;
import org.patikadev.ecommerce.service.CampaignService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;
    private final Validator<CreateCampaignRequest> createCampaignRequestValidator;
    private final Validator<Long> idValidator;

    @PostMapping
    public ResponseEntity<CreateCampaignResponse> create(@RequestBody CreateCampaignRequest request) {
        createCampaignRequestValidator.validate(request);
        return ResponseEntity.ok(campaignService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCampaignResponse> getCampaign(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(campaignService.getCampaignById(id));
    }

    @GetMapping("/string/{code}")
    public ResponseEntity<GetCampaignResponse> getCampaignByCode(@PathVariable String code) {
        return ResponseEntity.ok(campaignService.getCampaignByCode(code));
    }

    @GetMapping
    public ResponseEntity<Collection<GetCampaignResponse>> getAllCampaign() {
        return ResponseEntity.ok(campaignService.getAllCampaign());
    }
}
