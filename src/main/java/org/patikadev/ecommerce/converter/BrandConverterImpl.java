package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Brand;
import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BrandConverterImpl implements BrandConverter {
    @Override
    public Brand toCreateBrand(CreateBrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.name());
        brand.setCreatedAt(new Date());
        brand.setCreatedBy("Ahmetozcan");
        return brand;
    }

    @Override
    public GetBrandResponse toGetBrandResponse(Brand brand) {
        return new GetBrandResponse(brand.getId(),
                brand.getName());
    }

    @Override
    public CreateBrandResponse toCreateBrandResponse(Brand brand) {
        return new CreateBrandResponse(brand.getId(),
                brand.getName());
    }
}
