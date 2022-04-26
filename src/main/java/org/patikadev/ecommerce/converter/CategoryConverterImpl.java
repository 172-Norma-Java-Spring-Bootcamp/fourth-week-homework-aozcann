package org.patikadev.ecommerce.converter;

import org.patikadev.ecommerce.model.Category;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryParentResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public Category toCreateCategoryRequest(CreateCategoryRequest request) {
        Category category = new Category();
        category.setParent(request.parent());
        category.setName(request.name());
        return category;
    }

    @Override
    public CreateCategoryResponse toCreateCategoryResponse(Category category) {
        return new CreateCategoryResponse(category.getId(),
                category.getParent(),
                category.getName());
    }

    @Override
    public GetCategoryResponse toGetCategoryResponse(Category category) {
        GetCategoryParentResponse getCategoryParentResponse = new GetCategoryParentResponse(category.getParent().getId(),category.getParent().getName());
        return new GetCategoryResponse(category.getId(),
                getCategoryParentResponse,
                category.getName());
    }
}
