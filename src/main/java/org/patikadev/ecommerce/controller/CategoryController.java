package org.patikadev.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.patikadev.ecommerce.model.response.CreateCategoryResponse;
import org.patikadev.ecommerce.model.response.GetCategoryResponse;
import org.patikadev.ecommerce.service.CategoryService;
import org.patikadev.ecommerce.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {

    private final Validator<CreateCategoryRequest> createCategoryRequestValidator;
    private final Validator<Long> idValidator;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        createCategoryRequestValidator.validate(request);
        return ResponseEntity.ok(categoryService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping
    public ResponseEntity<Collection<GetCategoryResponse>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id,
                                                @RequestParam(name = "isHardDeleted", required = false) boolean isHardDeleted) {
        idValidator.validate(id);
        return ResponseEntity.ok(categoryService.deleteCategoryById(id, isHardDeleted));

    }

}
