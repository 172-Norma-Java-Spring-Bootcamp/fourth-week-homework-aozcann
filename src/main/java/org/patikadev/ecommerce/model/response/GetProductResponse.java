package org.patikadev.ecommerce.model.response;

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
