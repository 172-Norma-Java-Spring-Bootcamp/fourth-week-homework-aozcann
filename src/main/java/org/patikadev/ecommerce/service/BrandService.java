package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;

import java.util.Collection;

public interface BrandService {
    CreateBrandResponse create(CreateBrandRequest request);

    GetBrandResponse getBrand(Long id);

    Collection<GetBrandResponse> getAllBrand();

    boolean deleteBrandById(Long id,boolean isHardDeleted);
}
