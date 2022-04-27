package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.response.GetBrandResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductResponse(Long id,
                                 String name,
                                 BigDecimal price,
                                 UUID barcode,
                                 String image,
                                 GetBrandResponse brand,
                                 GetCategoryResponse category) {
}
