package org.patikadev.ecommerce.model.response;

public record GetCategoryResponse(Long id,
                                  String name,
                                  GetCategoryParentResponse parent) {
}
