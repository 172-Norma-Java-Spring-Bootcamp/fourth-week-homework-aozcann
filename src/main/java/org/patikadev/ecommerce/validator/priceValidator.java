package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

@Component
public class priceValidator implements Validator<BigDecimal> {
    @Override
    public void validate(BigDecimal price) throws BaseValidationException {
        if (price.compareTo(ZERO) < 0) {
            throw new ValidationOperationException.ProductPriceNotLessZeroValidException("Product price can not be less then zero.");
        }
        if (Objects.equals(price, ZERO)) {
            throw new ValidationOperationException.ProductPriceNotLessZeroValidException("Product price can not be equal zero.");
        }
    }
}
