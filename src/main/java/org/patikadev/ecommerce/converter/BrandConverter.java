package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Brand;
import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;

public interface BrandConverter {
    Brand toCreateBrandRequest(CreateBrandRequest request);

    GetBrandResponse toGetBrandResponse(Brand brand);

    CreateBrandResponse toCreateBrandResponse(Brand brand);
}
