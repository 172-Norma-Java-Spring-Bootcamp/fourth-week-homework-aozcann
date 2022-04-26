package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;

public interface Validator<T> {

    void validate(T input) throws BaseValidationException;
}
