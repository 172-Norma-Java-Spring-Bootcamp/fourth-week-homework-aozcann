package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Product;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.patikadev.ecommerce.model.response.CreateProductResponse;
import org.patikadev.ecommerce.model.response.GetBasketItemProductResponse;
import org.patikadev.ecommerce.model.response.GetProductResponse;

public interface ProductConverter {

    Product toCreateProduct(CreateProductRequest request);

    CreateProductResponse toCreateProductResponse(Product product);

    GetProductResponse toGetProductResponse(Product product);

    GetBasketItemProductResponse toGetBasketItemProductResponse(Product product);
}
