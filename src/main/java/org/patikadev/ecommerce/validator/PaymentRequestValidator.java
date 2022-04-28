package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.request.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PaymentRequestValidator implements Validator<PaymentRequest> {
    @Override
    public void validate(PaymentRequest request) throws BaseValidationException {
        if (Objects.isNull(request)) {
            throw new ValidationOperationException.PaymemtNotValidException("Payment can not be null or empty.");
        }
        if (Objects.isNull(request.paymentType())) {
            throw new ValidationOperationException.PaymemtNotValidException("Payment type can not be null or empty.");
        }
    }
}
