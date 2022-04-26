package org.patikadev.ecommerce.model.request;

import org.patikadev.ecommerce.model.Category;

public record CreateCategoryRequest(Category parent,
                                    String name) {
}
