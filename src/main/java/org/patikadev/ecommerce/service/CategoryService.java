package org.patikadev.ecommerce.service;

import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;

import java.util.Collection;

public interface CategoryService {

    CreateCategoryResponse create(CreateCategoryRequest request);

    GetCategoryResponse getCategory(Long id);

    Collection<GetCategoryResponse> getAllCategory();

    boolean deleteCategoryById(Long id,boolean isHardDeleted);
}
