package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.CreateBasketRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateBasketRequestValidator implements Validator<CreateBasketRequest>{
    @Override
    public void validate(CreateBasketRequest request) throws BaseValidationException {
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.BasketNotValidException("Basket can not be null or empty");
        }
        if ((CollectionUtils.isEmpty(request.items()))) {
            throw new ValidationOperationException.ProductNotValidException("Basket items can not be null or empty");
        }
        if (Objects.isNull(request.paymentType())) {
            throw new ValidationOperationException.CustomerNotValidException("Payment type can not be null or empty");
        }

    }
}
