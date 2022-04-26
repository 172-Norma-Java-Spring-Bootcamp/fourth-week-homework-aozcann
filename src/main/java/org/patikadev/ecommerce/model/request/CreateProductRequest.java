package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.response.CreateBrandResponse;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(String name,
                                   BigDecimal price,
                                   UUID barcode,
                                   String image,
                                   CreateBrandResponse brand,
                                   CreateCategoryResponse category) {
}
