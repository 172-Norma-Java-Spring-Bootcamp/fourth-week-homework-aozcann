package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Category;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;

public interface CategoryConverter {

    Category toCreateCategoryRequest(CreateCategoryRequest request);

    CreateCategoryResponse toCreateCategoryResponse(Category category);

    GetCategoryResponse toGetCategoryResponse(Category category);
}
