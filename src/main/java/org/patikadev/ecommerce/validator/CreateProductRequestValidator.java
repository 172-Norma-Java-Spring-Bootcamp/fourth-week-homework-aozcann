package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.CreateProductRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
@Component
public class CreateProductRequestValidator implements Validator<CreateProductRequest> {
    @Override
    public void validate(CreateProductRequest request) throws BaseValidationException {
        // fail first approach.
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.ProductNotValidException("Product can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.name()))) {
            throw new ValidationOperationException.ProductNotValidException("Product name can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.image()))) {
            throw new ValidationOperationException.CustomerNotValidException("Product image can not be null or empty");
        }
        if (Objects.isNull(request.barcode())) {
            throw new ValidationOperationException.CustomerNotValidException("Product barcode can not be null or empty");
        }
        if (Objects.isNull(request.price())) {
            throw new ValidationOperationException.CustomerNotValidException("Product price can not be null or empty");
        }
        // customer address should validate

    }

}
