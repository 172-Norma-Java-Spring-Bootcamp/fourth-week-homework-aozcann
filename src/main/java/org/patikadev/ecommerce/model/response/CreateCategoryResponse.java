package org.patikadev.ecommerce.model.response;

import org.patikadev.ecommerce.model.Category;

public record CreateCategoryResponse(Long id,
                                     Category parent,
                                     String name) {
}
