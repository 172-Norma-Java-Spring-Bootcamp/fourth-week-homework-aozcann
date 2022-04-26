package org.patikadev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.ecommerce.converter.CategoryConverter;
import org.patikadev.ecommerce.exception.BusinessServiceOperationException;
import org.patikadev.ecommerce.model.Category;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;
import org.patikadev.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public CreateCategoryResponse create(CreateCategoryRequest request) {
        Category category = categoryConverter.toCreateCategoryRequest(request);
        categoryRepository.save(category);
        log.info("Category created successfully by id -> {}", category.getId());
        return categoryConverter.toCreateCategoryResponse(category);
    }

    @Override
    public GetCategoryResponse getCategory(Long id) {
        Category category = categoryRepository.findByIdAndIsDeleted(id, false).orElseThrow(() -> new BusinessServiceOperationException.CategoryNotFoundException("Category not found."));
        log.info("Category returned successfully by id -> {}", id);
        return categoryConverter.toGetCategoryResponse(category);
    }

    @Override
    public Collection<GetCategoryResponse> getAllCategory() {
        Collection<GetCategoryResponse> getCategoryResponses = categoryRepository.findByIsDeleted(false)
                .stream()
                .map(categoryConverter::toGetCategoryResponse).toList();
        log.info("All category returned successfully.");
        return getCategoryResponses;
    }

    @Override
    public boolean deleteCategoryById(Long id, boolean isHardDeleted) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new BusinessServiceOperationException.CategoryNotFoundException("Category not found"));
        if (isHardDeleted) {
            categoryRepository.delete(category);
            log.info("Category hard deleted successfully.");
            return true;
        }
        if (category.isDeleted()) {
            log.error("Category already deleted by id -> {}", id);
            throw new BusinessServiceOperationException.
                    CustemerAlreadyDeletedException("Category already deleted.");
        }
        category.setDeleted(true);
        log.info("Category deleted successfully by id -> {}", id);
        categoryRepository.save(category);
        return true;
    }


}
