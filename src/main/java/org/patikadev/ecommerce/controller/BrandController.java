package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;
import org.patikadev.ecommerce.service.BrandService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/brands")
public class BrandController {

    private final Validator<CreateBrandRequest> createBrandValidator;
    private final Validator<Long> idValidator;
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<CreateBrandResponse> create(@RequestBody CreateBrandRequest request) {
        createBrandValidator.validate(request);
        return ResponseEntity.ok(brandService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBrandResponse> getBrand(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(brandService.getBrand(id));
    }

    @GetMapping
    public ResponseEntity<Collection<GetBrandResponse>> getAllBrand() {
        return ResponseEntity.ok(brandService.getAllBrand());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrandById(@PathVariable Long id,
                                             @RequestParam(name = "isHardDeleted", required = false) boolean isHardDeleted) {
        idValidator.validate(id);
        return ResponseEntity.ok(brandService.deleteBrandById(id, isHardDeleted));
    }

}
