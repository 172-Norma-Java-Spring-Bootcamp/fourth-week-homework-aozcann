package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.Brand;
import org.patikadev.ecommerce.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductResponse(Long id,
                                    String name,
                                    BigDecimal price,
                                    UUID barcode,
                                    String image,
                                    Brand brand,
                                    Category category) {
}
