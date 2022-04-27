package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Brand;
import org.patikadev.ecommerce.model.Category;
import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.model.response.GetBrandResponse;
import org.patikadev.ecommerce.model.response.GetCategoryParentResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;
import org.patikadev.ecommerce.repository.BrandRepository;
import org.patikadev.ecommerce.repository.CategoryRepository;
import org.patikadev.ecommerce.model.response.GetProductResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class ProductConverterImpl implements ProductConverter {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public Product toCreateProductRequest(CreateProductRequest request) {

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setBarcode(request.barcode());
        product.setImage(request.image());
        product.setCreatedAt(new Date());
        product.setCreatedBy("AhmetOzcan");

        Brand brand = brandRepository.findByIdAndIsDeleted(request.brand().id(), false).orElseThrow(() -> new BusinessServiceOperationException.BrandNotFoundException("Brand not found"));

        Category category = categoryRepository.findByIdAndIsDeleted(request.category().id(), false).orElseThrow(() -> new BusinessServiceOperationException.CategoryNotFoundException("Category not found"));

        product.setCategory(category);
        product.setBrand(brand);

        return product;
    }

    @Override
    public CreateProductResponse toCreateProductResponse(Product product) {
        return new CreateProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand(),
                product.getCategory());
    }

    @Override
    public GetProductResponse toGetProductResponse(Product product) {
        GetBrandResponse getBrandResponse = new GetBrandResponse(product.getBrand().getId(),product.getBrand().getName());
        GetCategoryParentResponse getCategoryParentResponse = new GetCategoryParentResponse(product.getCategory().getParent().getId(),product.getCategory().getParent().getName());
        GetCategoryResponse getCategoryResponse = new GetCategoryResponse(product.getCategory().getId(),getCategoryParentResponse,product.getCategory().getName());
        return new GetProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                getBrandResponse,
                getCategoryResponse);
    }


}
