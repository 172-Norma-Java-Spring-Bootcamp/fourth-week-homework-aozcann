package org.patikadev.ecommerce.model.response;

import java.math.BigDecimal;
import java.util.UUID;

public record GetBasketItemProductResponse(Long id,
                                           String name,
                                           BigDecimal price,
                                           UUID barcode,
                                           String image,
                                           GetBrandResponse brand,
                                           Long categoryId) {
}
