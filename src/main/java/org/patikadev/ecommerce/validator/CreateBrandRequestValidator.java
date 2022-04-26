package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.CreateBrandRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateBrandRequestValidator implements Validator<CreateBrandRequest> {
    @Override
    public void validate(CreateBrandRequest request) throws BaseValidationException {
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.BrandNotValidException("Brand can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.name()))) {
            throw new ValidationOperationException.BrandNotValidException("Brand name can not be null or empty");
        }
    }
}
