package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.model.response.GetProductResponse;

import java.util.Collection;

public interface ProductService {
    CreateProductResponse create(CreateProductRequest request);

    GetProductResponse getProduct(Long id);

    Collection<GetProductResponse> getAllProduct();

    boolean deleteProductById(Long id,boolean isHardDeleted);
}
