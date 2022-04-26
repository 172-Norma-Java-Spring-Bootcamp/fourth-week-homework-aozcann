package org.patikadev.ecommerce.model.response;

public record GetCategoryResponse(Long id,
                                  GetCategoryParentResponse parent,
                                  String name) {
}
