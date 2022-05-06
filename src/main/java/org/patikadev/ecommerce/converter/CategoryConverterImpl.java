package org.patikadev.ecommerce.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.Category;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryParentResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;
import org.patikadev.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CategoryConverterImpl implements CategoryConverter {

    private final CategoryRepository categoryRepository;

    @Override
    public Category toCreateCategory(CreateCategoryRequest request, Category parent) {
        Category category = new Category();
        category.setParent(parent);
        category.setName(request.name());
        category.setCreatedAt(new Date());
        category.setCreatedBy("AhmetOzcan");
        return category;
    }

    @Override
    public CreateCategoryResponse toCreateCategoryResponse(Category category) {
        return new CreateCategoryResponse(category.getId(),
                category.getName(),
                category.getParent());
    }

    @Override
    public GetCategoryResponse toGetCategoryResponse(Category category) {

        // If the category does not have any parent or parent already is deleted, it does not return in response.
        if (Objects.nonNull(category.getParent())
                && Objects.nonNull(categoryRepository.findByIdAndIsDeleted(category.getParent().getId(), false).orElse(null))) {
            GetCategoryParentResponse getCategoryParentResponse = new GetCategoryParentResponse(category.getParent().getId(), category.getParent().getName());
            return new GetCategoryResponse(category.getId(),
                    category.getName(),
                    getCategoryParentResponse);
        } else {
            return new GetCategoryResponse(category.getId(),
                    category.getName(),
                    null);
        }
    }
}
