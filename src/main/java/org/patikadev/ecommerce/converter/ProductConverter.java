package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.repository.GetProductResponse;

public interface ProductConverter {

    Product toCreateProductRequest(CreateProductRequest request);

    CreateProductResponse toCreateProductResponse(Product product);

    GetProductResponse toGetProductResponse(Product product);
}
