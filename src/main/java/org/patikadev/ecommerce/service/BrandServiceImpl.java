package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.BrandConverter;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Brand;
import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;
import org.patikadev.ecommerce.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandConverter brandConverter;

    @Override
    public CreateBrandResponse create(CreateBrandRequest request) {
        Brand brand = brandConverter.toCreateBrandRequest(request);
        brandRepository.save(brand);
        return brandConverter.toCreateBrandResponse(brand);
    }

    @Override
    public GetBrandResponse getBrand(Long id) {
        Brand brand = brandRepository.
                findByIdAndIsDeleted(id, false).
                orElseThrow(() -> new BusinessServiceOperationException.BrandNotFoundException("Brand not found"));
        log.info("Brand returned successfully by id -> {}", brand.getId());
        return brandConverter.toGetBrandResponse(brand);
    }

    @Override
    public Collection<GetBrandResponse> getAllBrand() {
        Collection<GetBrandResponse> brandResponseList = brandRepository
                .findAllByIsDeleted(false)
                .stream()
                .map(brandConverter::toGetBrandResponse)
                .toList();
        log.info("All brand list returned successfully");
        return brandResponseList;
    }

    @Override
    public boolean deleteBrandById(Long id, boolean isHardDeleted) {
        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.BrandNotFoundException("Brand not found"));
        if (isHardDeleted) {
            brandRepository.delete(brand);
            return true;
        }
        if (brand.isDeleted()) {
            log.error("Brand already deleted by ıd -> {}", id);
            throw new BusinessServiceOperationException.BrandAlreadyDeletedException("Brand already deleted.");
        }
        brand.setDeleted(true);
        brandRepository.save(brand);
        log.info("Brand deleted successfully.");

        return brand.isDeleted();
    }

}
