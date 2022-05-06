package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.CreateOrUpdateCustomerRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateCustomerRequestValidator implements Validator<CreateOrUpdateCustomerRequest> {
    @Override
    public void validate(CreateOrUpdateCustomerRequest request) throws BaseValidationException {
        // fail first approach.
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.CustomerNotValidException("Customer can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.userName()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer username can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.password()))) {
            throw new ValidationOperationException.CustomerNotValidException("Customer password can not be null or empty");
        }
        if (Objects.isNull(request.identity())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer identity can not be null or empty");
        }
        if (Objects.isNull(request.gender())) {
            throw new ValidationOperationException.CustomerNotValidException("Customer gender can not be null or empty");
        }

    }


}
