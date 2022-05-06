package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.CreateCategoryRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCategoryRequestValidator implements Validator<CreateCategoryRequest> {
    @Override
    public void validate(CreateCategoryRequest request) throws BaseValidationException {
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.CategoryNotValidException("Category can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.name()))) {
            throw new ValidationOperationException.CategoryNotValidException("Category name can not be null or empty");
        }
    }
}
